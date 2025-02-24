Use PS39199_Java5_ASMSQL;

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
