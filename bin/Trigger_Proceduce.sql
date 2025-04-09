Use PS39199_Java5_ASMSQL;

use master;
go
CREATE TRIGGER trg_CheckPendingOrder
ON Orders
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Check if the inserting user already has an order with status 'Pending' and Payment_method <> 'WHEN_RECEIVE'
    IF EXISTS (
        SELECT 1 
        FROM Orders o
        JOIN inserted i ON o.UserID = i.UserID
        WHERE o.Status = 'Pending' 
        AND o.Payment_method <> 'WHEN_RECEIVE'
    )
    BEGIN
        -- Prevent inserting a new order for this user
        RAISERROR ('User already has an order with status Pending.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END

    -- If no pending orders exist for this user, proceed with inserting the new order
    INSERT INTO Orders (UserID, Order_Date, Total_Amount, Status, Shipping_Address, Payment_method)
    SELECT UserID, Order_Date, Total_Amount, Status, Shipping_Address, Payment_method
    FROM inserted;
END;




go
CREATE TRIGGER trg_DeleteOrder
ON Orders
INSTEAD OF DELETE
AS
BEGIN
    SET NOCOUNT ON;

    -- Xóa chi tiết đơn hàng trước
    DELETE FROM Order_Details
    WHERE OrderID IN (SELECT OrderID FROM deleted);

    -- Xóa đơn hàng
    DELETE FROM Orders
    WHERE OrderID IN (SELECT OrderID FROM deleted);
END;
