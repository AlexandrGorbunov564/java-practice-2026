create table Product (
	id serial unique not null,
	name char(30) not null,
	cost integer check(cost > -1)
);

insert into Product (name, cost) values ('Milk', 129);
insert into Product (name, cost) values ('Bread', 59);
insert into Product (name, cost) values ('Yogurt', 79);
insert into Product (name, cost) values ('Cheese', 149);
select * from Product;