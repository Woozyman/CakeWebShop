/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Michael
 * Created: 30-11-2016
 */

drop database if exists cakeWebShop;
create database cakeWebShop;
use cakeWebShop;

create table users
(
    userid int(5) primary key auto_increment,
    firstname varchar(30) not null,
    lastname varchar(30) not null,
    email  varchar(50) not null,
    phone varchar(8) not null,					# tlf.nr. i dk er 8 (kan udvides)
    address varchar(50) not null,
    zip varchar(4) not null,					# 4 fordi i dk, grønland og færøerne bruges kun max 4 (kan udvides)
    password varchar(40) not null				# 40 fordi SHA1 er 40 char lang 
);
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('admin', 'istrator', 'admin@cakewebshop.com', '23374263', 'adressen 4a,', '2300', 'admin123');
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('Frey','Clante','fclante@gmail.com','50565150','Amagerfælledvej 47','2300','pass123');
SELECT * FROM users;

create table shopItems
(
    itemid int(5) primary key auto_increment,
    itemName varchar(150) not null,
    itemDescription varchar(150) not null,
    itemPicture varchar(50),
    itemPrice double(3,2) not null,
    discontinuedDate datetime
);

create table orders
(
	orderid int(5) primary key auto_increment,
    userid int(5),								# referere til users(userid)
    FOREIGN KEY (userid) REFERENCES users(userid),
    orderDate datetime,
    orderCakeCompletedDate datetime,
    orderDeliveryDate datetime
);

create table orderLines
(
	orderLineid int(10) primary key auto_increment,
    orderid int(5),
    FOREIGN KEY (orderid) REFERENCES orders(orderid),
    shopItemid int(5),
    FOREIGN KEY (shopItemid) REFERENCES shopItems(itemid),
    itemPrice double(3,2) not null				# ikke foreign key af historiske orsager.
);