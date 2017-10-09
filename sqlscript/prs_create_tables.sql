-- Re-Create Scheme for Safety
DROP SCHEMA IF EXISTS `PRS`;
CREATE SCHEMA `PRS`;

-- Use the specified DB
-- Just in case PRS.TableName fails
USE `PRS`;

-- Drop Table
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS PurchaseRequest;
DROP TABLE IF EXISTS PurchaseRequestLineItem;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Vendor;
DROP TABLE IF EXISTS Status;

-- Create the Tables
-- User Table
CREATE TABLE `PRS`.`User` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(20) NOT NULL,
  `Password` VARCHAR(15) NOT NULL,
  `FirstName` VARCHAR(20) NOT NULL,
  `LastName` VARCHAR(20) NOT NULL,
  `Phone` VARCHAR(12) NOT NULL,
  `Email` VARCHAR(75) NOT NULL,
  `IsReviewer` BIT(1) NOT NULL,
  `IsAdmin` BIT(1) NOT NULL,
  `Active` BIT(1) DEFAULT 1 NOT NULL,
  `DateCreated` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `DateUpdated` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `UpdatedByUser` INT DEFAULT 1 NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `UserName_UNIQUE` (`UserName` ASC));

-- Purchase Request Table
CREATE TABLE `PRS`.`PurchaseRequest` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT NOT NULL,
  `Description` VARCHAR(100) NOT NULL,
  `Justification` VARCHAR(255) NOT NULL,
  `DateNeeded` DATE NOT NULL,
  `DeliveryMode` VARCHAR(25) NOT NULL,
  `StatusID` INT NOT NULL,
  `Total` DECIMAL(10,2) NOT NULL,
  `SubmittedDate` DATE NOT NULL,
  `ReasonForRejection` VARCHAR(100),
  `Active` BIT(1) DEFAULT 1 NOT NULL,
  `DateCreated` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `DateUpdated` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `UpdatedByUser` INT DEFAULT 1 NOT NULL,
  PRIMARY KEY (`ID`));

-- Purchase Request Line Item Table
CREATE TABLE `PRS`.`PurchaseRequestLineItem` (
  `ID` INT NOT NULL,
  `PurchaseRequestID` INT NOT NULL,
  `ProductID` INT NOT NULL,
  `Quantity` INT NOT NULL,
  `Active` BIT(1) DEFAULT 1 NOT NULL,
  `DateCreated` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `DateUpdated` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `UpdatedByUser` INT DEFAULT 1 NOT NULL,
  PRIMARY KEY (`PurchaseRequestID`, `ProductID`, `ID`),
  UNIQUE INDEX `PurchaseRequestID_UNIQUE` (`PurchaseRequestID` ASC),
  UNIQUE INDEX `ProductID_UNIQUE` (`ProductID` ASC));

-- Product Table
CREATE TABLE `PRS`.`Product` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `VendorID` INT NOT NULL,
  `PartNumber` VARCHAR(50) NOT NULL,
  `Name` VARCHAR(150) NOT NULL,
  `Price` DECIMAL(10,2) NOT NULL,
  `Unit` VARCHAR(255),
  `PhotoPath` VARCHAR(255),
  `Active` BIT(1) DEFAULT 1 NOT NULL,
  `DateCreated` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `DateUpdated` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `UpdatedByUser` INT DEFAULT 1 NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `VendorID_UNIQUE` (`VendorID`, `PartNumber` ASC));

-- Vendor Table
CREATE TABLE `PRS`.`Vendor` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Code` VARCHAR(10) NOT NULL,
  `Name` VARCHAR(255) NOT NULL,
  `Address` VARCHAR(255) NOT NULL,
  `City` VARCHAR(255) NOT NULL,
  `State` VARCHAR(2) NOT NULL,
  `Zip` VARCHAR(5) NOT NULL,
  `Phone` VARCHAR(12) NOT NULL,
  `Email` VARCHAR(100) NOT NULL,
  `IsPreApproved` BIT(1) NOT NULL,
  `Active` BIT(1) DEFAULT 1 NOT NULL,
  `DateCreated` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `DateUpdated` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `UpdatedByUser` INT DEFAULT 1 NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Code_UNIQUE` (`Code` ASC));

-- Status Table
CREATE TABLE `PRS`.`Status` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Description` VARCHAR(20) NOT NULL,
  `Active` BIT(1) DEFAULT 1 NOT NULL,
  `DateCreated` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `DateUpdated` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `UpdatedByUser` INT DEFAULT 1 NOT NULL,
  PRIMARY KEY (`ID`));

-- Update Foreign Keys
ALTER TABLE `PRS`.`PurchaseRequest`
  ADD CONSTRAINT `UserID_fk`
FOREIGN KEY (`UserID`) REFERENCES `User` (`ID`);

ALTER TABLE `PRS`.`Product`
  ADD CONSTRAINT `VendorID_fk`
FOREIGN KEY (`VendorID`) REFERENCES `Vendor` (`ID`);

ALTER TABLE `PRS`.`PurchaseRequestLineItem`
  ADD CONSTRAINT `ProductID_fk`
FOREIGN KEY (`ProductID`) REFERENCES `Product` (`ID`);

ALTER TABLE `PRS`.`PurchaseRequestLineItem`
  ADD CONSTRAINT `PurchaseRequestID_fk`
FOREIGN KEY (`PurchaseRequestID`) REFERENCES `PurchaseRequest` (`ID`);

ALTER TABLE `PRS`.`PurchaseRequest`
  ADD CONSTRAINT `StatusID_fk`
FOREIGN KEY (`StatusID`) REFERENCES `Status` (`ID`);

-- Insert Data Into Table
-- Add 'SYSTEM' User
INSERT INTO `PRS`.`User`(`ID`,`UserName`,`Password`,`FirstName`,`LastName`,`Phone`,`Email`,`IsReviewer`,`IsAdmin`,`UpdatedByUser`)
VALUES(1, 'Admin', 'x', 'System', 'System', 'XXX-XXX-XXXX', 'system@test.com', 1, 1,1);
INSERT INTO `PRS`.`User`(`ID`,`UserName`,`Password`,`FirstName`,`LastName`,`Phone`,`Email`,`Active`)
VALUES(2, 'testuser', 'x', 'Test', 'User', 'XXX-XXX-XXXX', 'testuser@test.com', 1);
-- insert some rows into the Vendor table
INSERT into `PRS`.`Vendor` (`ID`, `Code`, `Name`, `Address`, `City`, `State`, `Zip`, `Phone`, `Email`, `IsPreApproved`)
VALUES
  (1, 'BB-1001   ', 'Best Buy', '100 Best Buy Street', 'Louisville', 'KY', '40207', '502-111-9099', 'geeksquad@bestbuy.com', 0),
  (2, 'AP-1001   ', 'Apple Inc', '1 Infinite Loop', 'Cupertino', 'CA', '95014', '800-123-4567', 'genius@apple.com', 0),
  (3, 'AM-1001   ', 'Amazon', '410 Terry Ave. North', 'Seattle', 'WA', '98109','206-266-1000', 'amazon@amazon.com', 1),
  (4, 'ST-1001   ', 'Staples', '9550 Mason Montgomery Rd', 'Mason', 'OH', '45040', '513-754-0235', 'support@orders.staples.com', 1),
  (5, 'MC-1001   ', 'Micro Center', '11755 Mosteller Rd', 'Sharonville', 'OH', '45241', '513-782-8500', 'support@microcenter.com', 1);

-- insert some rows into the Status table
INSERT into status (description) VALUES
  ('New'),
  ('Approved'),
  ('Rejected');

-- insert some rows into the Product table
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (1,1,'ME280LL','iPad Mini 2',296.99,NULL,NULL);
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (2,2,'ME280LL','iPad Mini 2',299.99,NULL,NULL);
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (3,3,'105810','Hammermill Paper, Premium Multi-Purpose Paper Poly Wrap',8.99,'1 Ream / 500 Sheets',NULL);
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (4,4,'122374','HammerMill® Copy Plus Copy Paper, 8 1/2\" x 11\", Case',29.99,'1 Case, 10 Reams, 500 Sheets per ream',NULL);
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (5,4,'784551','Logitech M325 Wireless Optical Mouse, Ambidextrous, Black ',14.99,NULL,NULL);
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (6,4,'382955','Staples Mouse Pad, Black',2.99,NULL,NULL);
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (7,4,'2122178','AOC 24-Inch Class LED Monitor',99.99,NULL,NULL);
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (8,4,'2460649','Laptop HP Notebook 15-AY163NR',529.99,NULL,NULL);
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (9,4,'2256788','Laptop Dell i3552-3240BLK 15.6\"',239.99,NULL,NULL);
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (10,4,'IM12M9520','Laptop Acer Acer™ Aspire One Cloudbook 14\"',224.99,NULL,NULL);
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (11,4,'940600','Canon imageCLASS Copier (D530)',99.99,NULL,NULL);
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (12,5,'228148','Acer Aspire ATC-780A-UR12 Desktop Computer',399.99,'','/images/AcerAspireDesktop.jpg');
INSERT INTO `PRS`.`Product` (`ID`,`VendorID`,`PartNumber`,`Name`,`Price`,`Unit`,`PhotoPath`) VALUES (13,5,'279364','Lenovo IdeaCentre All-In-One Desktop',349.99,'','/images/LenovoIdeaCenter.jpg');
