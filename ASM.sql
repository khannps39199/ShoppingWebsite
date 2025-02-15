Create database PS39199_Java5_ASMSQL;
use master
use PS39199_Java5_ASMSQL;
--drop database PS39199_Java5_ASMSQL;
--use PolyOE;
CREATE TABLE Users (
    UserID INT IDENTITY PRIMARY KEY,
    Username NVARCHAR(50) NOT NULL UNIQUE,
    password_hash NVARCHAR(255) NOT NULL,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    full_name NVARCHAR(100),
    Phone NVARCHAR(15),	
    Address NVARCHAR(255),
    Role NVARCHAR(20) DEFAULT 'Customer', -- Customer, Admin
    is_activated BIT DEFAULT 0,
    created_at DATETIME DEFAULT GETDATE()
);

CREATE TABLE Categories (
    CategoryID INT IDENTITY PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL UNIQUE,
    Description NVARCHAR(MAX),
    Created_At DATETIME DEFAULT GETDATE()
);

CREATE TABLE Products (
    ProductID INT IDENTITY PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Description NVARCHAR(MAX),
    Price DECIMAL(18, 2) NOT NULL,
    Discount DECIMAL(5, 2) DEFAULT 0, -- % giảm giá
    Stock INT NOT NULL,
    Image NVARCHAR(255), -- URL ảnh sản phẩm
    CategoryID INT NOT NULL, -- FK tới Category
    Created_At DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_Products_Category FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);
drop table Cart;
CREATE TABLE Cart (
    CartID INT IDENTITY PRIMARY KEY,
    UserID INT NOT NULL, -- FK tới Users
    ProductID INT NOT NULL Unique, -- FK tới Products
    Quantity INT NOT NULL DEFAULT 1,	
    Added_At DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_Cart_User FOREIGN KEY (UserID) REFERENCES Users(UserID),
    CONSTRAINT FK_Cart_Product FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

CREATE TABLE Orders (
    OrderID INT IDENTITY PRIMARY KEY,
    UserID INT NOT NULL, -- FK tới Users
    OrderDate DATETIME DEFAULT GETDATE(),
    TotalAmount DECIMAL(18, 2) NOT NULL,
    Status NVARCHAR(50) DEFAULT 'Pending', -- Pending, Shipped, Delivered, Cancelled
    ShippingAddress NVARCHAR(255),
    CONSTRAINT FK_Orders_User FOREIGN KEY (UserID) REFERENCES Users(UserID)
);
CREATE TABLE OrderDetails (
    OrderDetailID INT IDENTITY PRIMARY KEY,
    OrderID INT NOT NULL, -- FK tới Orders
    ProductID INT NOT NULL, -- FK tới Products
    Quantity INT NOT NULL,
    Price DECIMAL(18, 2) NOT NULL, -- Giá tại thời điểm mua
    Discount DECIMAL(5, 2) DEFAULT 0,
    CONSTRAINT FK_OrderDetails_Order FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    CONSTRAINT FK_OrderDetails_Product FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
CREATE TABLE UserActivity (
    ActivityID INT IDENTITY PRIMARY KEY,
    UserID INT NOT NULL, -- FK tới Users
    ActivityType NVARCHAR(50), -- Register, ResetPassword, ChangePassword, etc.
    ActivityDate DATETIME DEFAULT GETDATE(),
    Token NVARCHAR(255), -- Mã xác thực (nếu cần)
    IsCompleted BIT DEFAULT 0,
    CONSTRAINT FK_UserActivity_User FOREIGN KEY (UserID) REFERENCES Users(UserID)
);
USE master;
ALTER DATABASE msdb SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
DBCC CHECKDB (msdb, REPAIR_ALLOW_DATA_LOSS);
ALTER DATABASE msdb SET MULTI_USER;
