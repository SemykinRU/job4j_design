create table markets(id serial primary key,
					name varchar(255),
					code int,
					mainIPadress inet
					);
					
create table positions(id serial primary key,
					 name varchar(255)
					 );
					 
create table employees(id serial primary key,
					  name varchar(255),
					  positions_id int references positions(id)
					  );

create table distribution(id serial primary key,
						 employees_id int references employees(id),
						 markets_id int references markets(id)
						 );
						 
create table access_code(id serial primary key,
						code int,
						activated bool
						);
						
create table employees_access_code(id serial primary key,
								  employees_id int references employees(id) unique,
								  access_code int references access_code(id) unique
								  );						
