CREATE DATABASE PS39199_Java5_ASMSQL;
USE PS39199_Java5_ASMSQL;
USE master;
-- Bảng Users
CREATE TABLE Users (
    UserID INT IDENTITY PRIMARY KEY,
    Username NVARCHAR(50) NOT NULL UNIQUE,
    password_hash NVARCHAR(255) NOT NULL,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    full_name NVARCHAR(100),
    Phone NVARCHAR(15),    
    Address NVARCHAR(255),
    Role NVARCHAR(20) DEFAULT 'Customer',
    is_activated BIT DEFAULT 0,
    created_at DATETIME DEFAULT GETDATE()
);

-- Bảng Categories
CREATE TABLE Categories (
    CategoryID INT IDENTITY PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL UNIQUE,
    Description NVARCHAR(MAX),
    Created_At DATETIME DEFAULT GETDATE()
);

-- Bảng Products
CREATE TABLE Products (
    ProductID INT IDENTITY PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Description NVARCHAR(MAX),
    Price DECIMAL(18, 2) NOT NULL,
    Discount DECIMAL(5, 2) DEFAULT 0,
    Stock INT NOT NULL,
    Image NVARCHAR(255),
    CategoryID INT NOT NULL,
    Created_At DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_Products_Category FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);
<<<<<<< HEAD
--drop table Cart;
=======

-- 22/02 update fix lỗi duplicate đối với user khác thêm giửo hàng ngoài john
-- step: drop table Cart -> recreate table Cart
drop table Cart;
>>>>>>> 9483cc21986f11a4504dd6277e833ea841618634
CREATE TABLE Cart (
    CartID INT IDENTITY PRIMARY KEY,
    UserID INT NOT NULL, -- FK tới Users
    ProductID INT NOT NULL , -- FK tới Products
    Quantity INT NOT NULL DEFAULT 1,	
    Added_At DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_Cart_User FOREIGN KEY (UserID) REFERENCES Users(UserID),
    CONSTRAINT FK_Cart_Product FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

go
drop table Order_Details ;
go
drop table Orders;
CREATE TABLE Orders (
    OrderID INT IDENTITY PRIMARY KEY,
    UserID INT NOT NULL, -- FK tới Users
    Order_Date DATETIME DEFAULT GETDATE(),
    Total_Amount DECIMAL(18, 2) NOT NULL,
	Payment_method NVARCHAR(50) DEFAULT 'WHEN_RECEIVE',
    Status NVARCHAR(50) DEFAULT 'Pending', -- Pending, Shipped, Delivered, Cancelled
    Shipping_Address NVARCHAR(255),
    CONSTRAINT FK_Orders_User FOREIGN KEY (UserID) REFERENCES Users(UserID)
);


CREATE TABLE Order_Details (
    OrderDetailID INT IDENTITY PRIMARY KEY,
    OrderID INT NOT NULL, -- FK tới Orders
    ProductID INT NOT NULL, -- FK tới Products
    Quantity INT NOT NULL,
    Price DECIMAL(18, 2) NOT NULL,
    Discount DECIMAL(5, 2) DEFAULT 0,
    CONSTRAINT FK_OrderDetails_Order FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    CONSTRAINT FK_OrderDetails_Product FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
/* =======
-- 
-- Bảng Orders
CREATE TABLE Orders (
    OrderID INT IDENTITY PRIMARY KEY,
    order_date DATETIME DEFAULT GETDATE(),
    total_amount DECIMAL(18, 2) NOT NULL,
    shipping_address NVARCHAR(255)
);

-- Bảng user_orders (Liên kết Users và Orders)
CREATE TABLE user_orders (
    user_order_id INT IDENTITY PRIMARY KEY,
    UserID INT NOT NULL,
    OrderID INT NOT NULL,
    Status NVARCHAR(50) DEFAULT 'Pending',
    Updated_At DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_UserOrders_User FOREIGN KEY (UserID) REFERENCES Users(UserID),
    CONSTRAINT FK_UserOrders_Order FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

-- Bảng order_details
CREATE TABLE order_details (
    order_detail_id INT IDENTITY PRIMARY KEY,
    OrderID INT NOT NULL,
    ProductID INT NOT NULL,
>>>>>>> de50cf07eb95142a927569753ebbde0780e6c7e1 */

-- Bảng UserActivity
CREATE TABLE UserActivity (
    ActivityID INT IDENTITY PRIMARY KEY,
    UserID INT NOT NULL,
    ActivityType NVARCHAR(50),
    ActivityDate DATETIME DEFAULT GETDATE(),
    Token NVARCHAR(255),
    IsCompleted BIT DEFAULT 0,
    CONSTRAINT FK_UserActivity_User FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

USE master;
ALTER DATABASE msdb SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
DBCC CHECKDB (msdb, REPAIR_ALLOW_DATA_LOSS);
ALTER DATABASE msdb SET MULTI_USER;
