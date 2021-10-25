create table type(id serial primary key,
				  name text
				 );
				 
create table product(id serial primary key,
					 name text,
					 type_id int references type(id),
					 expired_date date,
					 price float
					);
				 
insert into type(name) values('Сыр');
insert into type(name) values('Морепродукты');
insert into type(name) values('Молоко');
insert into type(name) values('Мясо');

insert into product(name, type_id, expired_date, price) values('Сыр брынза', '1', date '2021.11.30', '120');
insert into product(name, type_id, expired_date, price) values('Сыр голандский', '1', date '2021.10.20', '150');
insert into product(name, type_id, expired_date, price) values('Янтарный сырок 1', '1', date '2021.11.20', '100');
insert into product(name, type_id, expired_date, price) values('Янтарный сырок 2', '1', date '2021.11.20', '100');
insert into product(name, type_id, expired_date, price) values('Янтарный сырок 3', '1', date '2021.11.20', '100');
insert into product(name, type_id, expired_date, price) values('Янтарный сырок 4', '1', date '2021.11.20', '100');
insert into product(name, type_id, expired_date, price) values('Янтарный сырок 5', '1', date '2021.11.20', '100');
insert into product(name, type_id, expired_date, price) values('Янтарный сырок 6', '1', date '2021.11.20', '100');
insert into product(name, type_id, expired_date, price) values('Янтарный сырок 7', '1', date '2021.11.20', '100');
insert into product(name, type_id, expired_date, price) values('Янтарный сырок 8', '1', date '2021.11.20', '100');
insert into product(name, type_id, expired_date, price) values('Янтарный сырок 9', '1', date '2021.11.20', '100');
insert into product(name, type_id, expired_date, price) values('Креветки', '2', date '2021.10.30', '450');
insert into product(name, type_id, expired_date, price) values('Судак', '2', date '2021.10.30', '170');
insert into product(name, type_id, expired_date, price) values('Карась', '2', date '2021.10.20', '100');
insert into product(name, type_id, expired_date, price) values('Молоко', '3', date '2021.10.24', '90');
insert into product(name, type_id, expired_date, price) values('Кефир', '3', date '2021.10.24', '70');
insert into product(name, type_id, expired_date, price) values('Йогурт', '3', date '2021.11.15', '120');
insert into product(name, type_id, expired_date, price) values('Мороженое забава', '3', date '2022.01.15', '70');
insert into product(name, type_id, expired_date, price) values('Мороженое мясо', '4', date '2022.01.15', '300');
insert into product(name, type_id, expired_date, price) values('Мороженое кур бедро', '4', date '2022.01.15', '150');
insert into product(name, type_id, expired_date, price) values('Делифинятина мороженое', '4', date '2022.01.15', '150');
insert into product(name, type_id, expired_date, price) values('Хек мороженый', '4', date '2022.01.15', '150');

select p.name as Название_продукта, pp.name as Тип_продукта, p.expired_date as Годе_до, p.price as Цена from product p join type as pp on p.type_id = pp.id where pp.name like 'Сыр';
select * from product where name LIKE 'мороженое' 
							or name LIKE 'мороженое%' 
							or name LIKE '%мороженое%' 
							or name LIKE '%мороженое' 
							or name LIKE 'Мороженое' 
							or name LIKE 'Мороженое%' 
							or name LIKE '%Мороженое%' 
							or name LIKE '%Мороженое';
							
select * from product where expired_date < current_date;
select name, price from product where price = (select max(price) from product);
select t.name as Имя_типа, count(tt.type_id) as Количество from product tt join type as t on tt.type_id = t.id group by t.name;
select p.name as Название_продукта, pp.name as Тип_продукта, p.expired_date as Годе_до, p.price as Цена from product p join type as pp on p.type_id = pp.id where pp.name like 'Сыр' or pp.name like 'Молоко';
select t.name as Имя_типа, count(tt.type_id) as Количество from product tt join type as t on tt.type_id = t.id group by t.name having count(tt.type_id) < 10;
select t.name as Название_продукат, tt.name as Тип_продукта from product t join type as tt on t.type_id = tt.id;
