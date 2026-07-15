create table Product (
	id serial unique not null,
	name char(30) not null,
	cost integer check(cost > -1)
);

insert into Product (name, cost) values ('Milk', 129);
insert into Product (name, cost) values ('Bread', 59);
insert into Product (name, cost) values ('Yogurt', 79);
insert into Product (name, cost) values ('Cheese', 149);
insert into Product (name, cost) values ('Milk', 139);
insert into Product (name, cost) values ('Bread', 69);
insert into Product (name, cost) values ('Yogurt', 89);
insert into Product (name, cost) values ('Cheese', 139);
insert into Product (name, cost) values ('Milk', 119);
insert into Product (name, cost) values ('Bread', 79);
insert into Product (name, cost) values ('Yogurt', 69);
insert into Product (name, cost) values ('Cheese', 159);
insert into Product (name, cost) values ('Milk', 149);
insert into Product (name, cost) values ('Bread', 39);
insert into Product (name, cost) values ('Yogurt', 99);
insert into Product (name, cost) values ('Cheese', 150);
insert into Product (name, cost) values ('Milk', 130);
insert into Product (name, cost) values ('Bread', 60);
insert into Product (name, cost) values ('Yogurt', 80);
insert into Product (name, cost) values ('Bread', 50);
insert into Product (name, cost) values ('Yogurt', 90);
insert into Product (name, cost) values ('Cheese', 155);
insert into Product (name, cost) values ('Milk', 135);
insert into Product (name, cost) values ('Bread', 65);
insert into Product (name, cost) values ('Yogurt', 85);
insert into Product (name, cost) values ('Bread', 45);
insert into Product (name, cost) values ('Yogurt', 83);
insert into Product (name, cost) values ('Cheese', 160);
insert into Product (name, cost) values ('Milk', 123);
insert into Product (name, cost) values ('Bread', 57);
insert into Product (name, cost) values ('Yogurt', 77);
select * from Product;