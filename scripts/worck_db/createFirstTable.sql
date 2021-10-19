create table markets(id serial primary key,
					name varchar(255),
					code int,
					mainIPadress inet);

insert into markets(name, code, mainIPadress)
			values('ММ Тестовый', '999922', '10.3.8.25');

update markets set name = 'ММ Измененный', 
				code = '111222', 
				mainipadress = '8.8.8.8';

delete from markets;