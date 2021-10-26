create table departments(id serial primary key,
						name text
						);
						
create table emploers(id serial primary key,
					 name text,
					 department_id int references departments(id)
					 );
					 
create table teens(id serial primary key,
				  name text,
				  gender varchar(255)
				  );
					 
insert into departments(name) values('manager');
insert into departments(name) values('programmer');
insert into departments(name) values('ingener');
insert into departments(name) values('tester');

insert into emploers(name, department_id) values('Manager 1', '1');
insert into emploers(name, department_id) values('Manager 2', '1');
insert into emploers(name, department_id) values('Manager 3', '1');
insert into emploers(name, department_id) values('Programmer 1', '2');
insert into emploers(name, department_id) values('Programmer 2', '2');
insert into emploers(name, department_id) values('Programmer 3', '2');
insert into emploers(name, department_id) values('Programmer 4', '2');
insert into emploers(name, department_id) values('Ingener 1', '3');
insert into emploers(name, department_id) values('Ingener 2', '3');
insert into emploers(name, department_id) values('Stager 1', null);
insert into emploers(name, department_id) values('Stager 2', null);
insert into emploers(name, department_id) values(null, '4');
insert into emploers(name, department_id) values(null, '4');
insert into emploers(name, department_id) values(null, '4');

insert into teens(name, gender) values('Мальчик 1', 'male');
insert into teens(name, gender) values('Девочка 1', 'female');
insert into teens(name, gender) values('Мальчик 2', 'male');
insert into teens(name, gender) values('Девочка 2', 'female');
insert into teens(name, gender) values('Девочка 3', 'female');

select * from emploers e left join departments d on e.department_id = d.id;
select * from emploers e right join departments d on e.department_id = d.id;
select * from emploers e full join departments d on e.department_id = d.id;
select * from emploers e cross join departments d;
select * from departments d left join emploers e on e.department_id = d.id
    where e.name is null;
select * from emploers e left join departments d on e.department_id = d.id;
select * from departments d right join emploers e on e.department_id = d.id;
select t1.name as Первый_претендент, t2.name as Второй_претендент,
    t1.name || ' - ' || t2.name as Возможная_пара
	    from teens t1
	    cross join teens t2
	    where t1.gender != t2.gender;