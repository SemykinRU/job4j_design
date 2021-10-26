create table bodies(id serial primary key,
				 name text
				 );
				 
create table engines(id serial primary key,
				 name text
				 );
				 
create table gearboxes(id serial primary key,
				 name text
				 );	
				 
create table cars(id serial primary key,
				 name text,
				 body_id int references bodies(id),
				 engine_id int references engines(id),
				 gearbox_id int references gearboxes(id)
				 );
				 
insert into bodies(name) values('red body 1');
insert into bodies(name) values('blue body 2');
insert into bodies(name) values('green body 3');
insert into bodies(name) values('black body 4');
insert into bodies(name) values('white body 5');

insert into engines(name) values('engine 1');
insert into engines(name) values('engine 2');
insert into engines(name) values('engine 3');
insert into engines(name) values('engine 4');
insert into engines(name) values('engine 5');

insert into gearboxes(name) values('gearbox 1');
insert into gearboxes(name) values('gearbox 2');
insert into gearboxes(name) values('gearbox 3');
insert into gearboxes(name) values('gearbox 4');
insert into gearboxes(name) values('gearbox 5');

insert into cars(name, body_id, engine_id, gearbox_id) 
		values('car 1', '1', '1', '1');
insert into cars(name, body_id, engine_id, gearbox_id) 
		values('car 2', '2', '2', '2');
insert into cars(name, body_id, engine_id, gearbox_id) 
		values('car 3', '3', '3', '3');

select c.name as Машина, 
		b.name as Кузов, 
		e.name as Двигатель, 
		g.name as Коробка_передач 
	from cars c 
	left join bodies b on c.body_id = b.id 
	left join engines e on c.engine_id = e.id 
	left join gearboxes g on c.gearbox_id = g.id;
	
select b.name as Не_используемые_кузова 
	from bodies b 
	left join cars c on b.id = c.body_id 
	where c.body_id is null;
	
select e.name as Не_используемые_двигателя 
	from engines e 
	left join cars c on e.id = c.body_id 
	where c.body_id is null;
	
select g.name as Не_используемые_коробки_передач
	from gearboxes g 
	left join cars c on g.id = c.body_id 
	where c.body_id is null;