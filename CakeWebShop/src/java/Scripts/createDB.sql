drop database if exists cakeWebShop;
create database cakeWebShop;
use cakeWebShop;

create table users
(
    userid int(5) primary key auto_increment,                   # BrugerID
    firstname varchar(30) not null,                             # Fornavn
    lastname varchar(30) not null,                              # Efternavn
    email  varchar(50) not null,                                # E-mail addresse
    phone varchar(8) not null,									# tlf.nr. i dk er 8 (kan udvides)
    address varchar(50) not null,                               # Adresse
    zip varchar(4) not null,									# 4 fordi i dk, grønland og færøerne bruges kun max 4 (kan udvides)
    password varchar(100) not null								# 100 fordi Hashen er lang
);
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('admin', 'istrator', 'admin@cakewebshop.com', '11111111', 'adressen 4a,', '2300', 'sha1:64000:18:FN2lYGJGvuvCA24q03hqyZoVQAW3ogHd:HSwKPF/53VNjeBpeI/AD/zWb');
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('guest', 'user', 'guest@cakewebshop.com', '2222222', 'adressen 4b,', '2300', 'sha1:64000:18:M/dg26HYd7T0RDEB5R/oJFx6pnWd60V5:YWtiqx7fSxuj2SSy49a9X5eP');
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('baker', 'user', 'baker@cakewebshop.com', '3333333', 'adressen 4c,', '2300', 'sha1:64000:18:wAQY8vsbSP6SgeJm3B3/ckTmEmXA3ptA:9DoC+lrCKj3KHknobygZjY/a');
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('Frey','Clante','frey@cakewebshop.com','44444444','adressen 4d','2300','sha1:64000:18:yfNu77tAVdUMlsY5zD1b8/nw722aMIfR:3wdVtZWMV3FJA5rzSLeOy0H/');
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('Jens','Kolby','jens@cakewebshop.com','55555555','adressen 4e','2300','sha1:64000:18:yfNu77tAVdUMlsY5zD1b8/nw722aMIfR:3wdVtZWMV3FJA5rzSLeOy0H/');
INSERT INTO users (firstname, lastname, email, phone, address, zip, password) VALUES ('Michael','Hansen','michael@cakewebshop.com','66666666','adressen 4f','2300','sha1:64000:18:yfNu77tAVdUMlsY5zD1b8/nw722aMIfR:3wdVtZWMV3FJA5rzSLeOy0H/');

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
INSERT INTO shopItems (itemName, itemDescription, itemPicture, itemPrice) VALUES ('Batmankage','Fandant','images/batman.jpg',79.5);
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
    userid int(5),												# referere til users(userid)
    FOREIGN KEY (userid) REFERENCES users(userid),
    orderDate datetime,
    orderCakeCompletedDate datetime,
    orderDeliveryDate datetime,
    orderInShoppingCart TINYINT
);

create table orderLines
(
    orderLineid int(10) primary key auto_increment,
    orderid int(5),
    FOREIGN KEY (orderid) REFERENCES orders(orderid),
    shopItemid int(5),
    FOREIGN KEY (shopItemid) REFERENCES shopItems(itemid),
    numberOfItems int(3),
    itemPrice double(5,2) not null								# ikke foreign key af historiske orsager.
);
