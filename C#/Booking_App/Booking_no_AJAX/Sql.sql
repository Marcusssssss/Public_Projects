create database Booking

go

use Booking

go

create table Hotels(
	Id int primary key auto_increment,
    Name varchar(50) not null,
    Stars int not null,
    Location varchar(50) not null
)

create table Clients(
	Id int primary key auto_increment,
    Name varchar(50) not null,
    Email varchar(50) not null
)

create table Rooms(
	Id int primary key auto_increment,
    Category varchar(50) not null,
    Type varchar(50) not null,
    Price float not null,
    HId int references Hotels(Id)
)

create table Reservations(
	Id int primary key auto_increment,
	CId int references Clients(Id),
    RId int references Rooms(Id),
    StartDate date not null,
    EndDate date not null
)

insert into Hotels(Name, Stars, Location) Values ('Lux', 5, 'Iasi'), ('SemiLux', 3, 'Targoviste'), ('ExtraLux', 6, 'Bucuresti');

insert into Rooms(Category, Type, Price, HId) values ('Single', 'Deluxe', 300, 1), ('Double', 'King', 400, 1), ('Studio', 'Relax', 200, 1);
insert into Rooms(Category, Type, Price, HId) values ('Single', 'Deluxe', 100, 2), ('Double', 'King', 200, 2), ('Studio', 'Relax', 100, 2);
insert into Rooms(Category, Type, Price, HId) values ('Single', 'Deluxe', 800, 3), ('Double', 'King', 1000, 3), ('Studio', 'Relax', 500, 3);