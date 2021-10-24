create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('Телефон', '15000');
insert into devices(name, price) values('Планшет', '25000');
insert into devices(name, price) values('Ноутбук', '150000');
insert into devices(name, price) values('Наушники', '4999');

insert into people(name) values('Пётр');
insert into people(name) values('Олег');
insert into people(name) values('Иван');

insert into devices_people(device_id, people_id) values('1', '1');
insert into devices_people(device_id, people_id) values('2', '1');
insert into devices_people(device_id, people_id) values('3', '1');
insert into devices_people(device_id, people_id) values('2', '2');
insert into devices_people(device_id, people_id) values('3', '2');
insert into devices_people(device_id, people_id) values('4', '2');
insert into devices_people(device_id, people_id) values('4', '3');

select avg(p.price) as Средняя_цена_девайсов from devices as p;
select avg(d.price) as Средняя_цена_девайсов, p.name as Имя_пипла from devices_people as dp join devices d on dp.device_id = d.id join people p on dp.people_id = p.id group by p.name;
select avg(d.price) as Средняя_цена_девайсов, p.name as Имя_пипла from devices_people as dp join devices d on dp.device_id = d.id join people p on dp.people_id = p.id group by p.name having avg(d.price) > 5000;
