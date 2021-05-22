DROP TABLE Cars;

CREATE TABLE `cars`(
    `id` int primary key auto_increment,
    `model` varchar(255),
    `hp` int,
    `fuel` varchar(99),
    `price` double,
    `color` varchar(255),
    `age` int
);

insert into cars values(null,'BMW X3',190,'petrol',55000.12, 'black', 2);
insert into cars values(null,'Skoda Rapid',99,'petrol',6000.2, 'brunelo red', 8);
select * from cars;