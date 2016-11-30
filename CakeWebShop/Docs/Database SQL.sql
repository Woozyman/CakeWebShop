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

create table shopItems
(
	itemid int(5) primary key auto_increment,
    itemName varchar(150) not null,
    itemPrice double(3,2) not null
);

create table orders
(
	orderid int(5) primary key auto_increment,
    userid int(5),								# referere til users(userid)
    FOREIGN KEY (userid) REFERENCES users(userid),
    orderStatus int(1),							# 0=ikke behandlet 1=sendt
    orderDate datetime,
    orderDeliveryDate datetime
);

create table orderLines
(
	orderLineid int(10) primary key auto_increment,
    orderid int(5),
    FOREIGN KEY (orderid) REFERENCES orders(orderid),
    shopItem int(5),
    FOREIGN KEY (shopItem) REFERENCES shopItems(itemid),
    itemPrice double(3,2) not null				# ikke foreign key af historiske orsager.
);