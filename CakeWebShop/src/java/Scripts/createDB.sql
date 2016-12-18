drop database if exists cakeWebShop;
create database cakeWebShop;
use cakeWebShop;

create table users
(
    userid int(5) primary key auto_increment,                   # BrugerID
    firstname varchar(30) not null,                             # Fornavn
    lastname varchar(30) not null,                              # Efternavn
    email  varchar(50) not null,                                # E-mail addresse
    phone varchar(8) not null,					# tlf.nr. i dk er 8 (kan udvides)
    address varchar(50) not null,                               # Adresse
    zip varchar(4) not null,					# 4 fordi i dk, grønland og færøerne bruges kun max 4 (kan udvides)
    password varchar(100) not null				# 100 fordi Hashen er lang
);
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('admin', 'istrator', 'admin@cakewebshop.com', '11111111', 'adressen 4a,', '2300', 'admin123');
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('guest', 'user', 'guest@cakewebshop.com', '2222222', 'adressen 4b,', '2300', 'guest123');
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('Frey','Clante','frey@cakewebshop.com','33333333','adressen 4c','2300','pass123');
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('Jens','Kolby','jens@cakewebshop.com','44444444','adressen 4d','2300','pass123');
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('Michael','Hansen','michael@cakewebshop.com','55555555','adressen 4e','2300','pass123');

create table shopItems
(
    itemid int(5) primary key auto_increment,
    itemName varchar(150) not null,
    itemDescription varchar(150) not null,
    itemPicture varchar(50),
    itemPrice double(5,2) not null,
    discontinuedDate datetime
);
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('Fødselsdags Lagkage','Vanillie/Banan','images/lagkage.jpg',149.5);
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('Chokoladekage','Chokolade','images/chokolade.jpg',99.5);
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('Babykage','Vanillie','images/babykage.jpg',149.5);
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('Flagkage','Vanillie/flødeskum','images/flag.jpg',49.5);
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('Batmankage','Fandant','images/Batman.jpg',79.5);
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('Bryllupskage','Frugter','images/bryllup.jpg',349.5);
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('CocaCola kage','Fløde','images/cola.jpg',59.5);
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('Minions','Banan','images/minion.jpg',99.5);
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('Pistache','Pistache skærekage','images/pistache.jpg',39.5);
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('Cheesecake','Cheesecake/Raspberry','images/raspberrycheese.jpg',89.5);
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('Rosekage','Jordbær','images/rose.jpg',109.5);
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('Toffifee-kage','Chokolade/Toffifee','images/toffifee.jpg',69.5);

create table orders
(
    orderid int(5) primary key auto_increment,
    userid int(5),								# referere til users(userid)
    FOREIGN KEY (userid) REFERENCES users(userid),
    orderDate datetime,
    orderCakeCompletedDate datetime,
    orderDeliveryDate datetime,
    orderInShoppingCart TINYINT
);
INSERT INTO orders (userid, orderDate, orderCakeCompletedDate, orderDeliveryDate, orderInShoppingCart) VALUES (2, NULL, NULL, NULL, 1);
INSERT INTO orders (userid, orderDate, orderCakeCompletedDate, orderDeliveryDate, orderInShoppingCart) VALUES (3, '2016-12-10 10:15:00', NULL, '2016-12-20 12:00:00', 0);
INSERT INTO orders (userid, orderDate, orderCakeCompletedDate, orderDeliveryDate, orderInShoppingCart) VALUES (3, '2016-12-11 12:05:00', NULL, '2016-12-22 12:00:00', 0);
INSERT INTO orders (userid, orderDate, orderCakeCompletedDate, orderDeliveryDate, orderInShoppingCart) VALUES (4, '2016-12-12 15:45:00', NULL, '2016-12-21 12:00:00', 0);
INSERT INTO orders (userid, orderDate, orderCakeCompletedDate, orderDeliveryDate, orderInShoppingCart) VALUES (5, '2016-12-13 18:25:00', NULL, '2016-12-22 12:00:00', 0);

create table orderLines
(
    orderLineid int(10) primary key auto_increment,
    orderid int(5),
    FOREIGN KEY (orderid) REFERENCES orders(orderid),
    shopItemid int(5),
    FOREIGN KEY (shopItemid) REFERENCES shopItems(itemid),
    numberOfItems int(3),
    itemPrice double(5,2) not null				# ikke foreign key af historiske orsager.
);
INSERT INTO orderLines (orderid, shopItemid, numberOfItems, itemPrice) VALUES (1, 1, 1, 149.5);
INSERT INTO orderLines (orderid, shopItemid, numberOfItems, itemPrice) VALUES (2, 6, 1, 349.5);
INSERT INTO orderLines (orderid, shopItemid, numberOfItems, itemPrice) VALUES (3, 9, 10, 39.5);
INSERT INTO orderLines (orderid, shopItemid, numberOfItems, itemPrice) VALUES (3, 12, 1, 149.5);
INSERT INTO orderLines (orderid, shopItemid, numberOfItems, itemPrice) VALUES (4, 2, 1, 99.5);
INSERT INTO orderLines (orderid, shopItemid, numberOfItems, itemPrice) VALUES (5, 4, 2, 49.5);