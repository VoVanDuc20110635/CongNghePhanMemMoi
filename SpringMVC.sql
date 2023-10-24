create database springmvcOrder;
use springmvcOrder;

drop table `orders`;
create table `Orders` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` nvarchar(50) DEFAULT NULL,
  `Phone` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `ordername` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

select * from orders;