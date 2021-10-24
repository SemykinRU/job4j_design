create table author(id serial primary key,
				   name varchar(255)
				   );
				 
create table barcode(id serial primary key,
				  code int
				  );
				  
create table publisher(id serial primary key,
					  name text
					  );
					  
create table book(id serial primary key,
				 name text,
				 author_id int references author(id),
				 publisher_id int references publisher(id),
				 barcode_id int references barcode(id) 
				 );					  
					  				 
insert into author(name) values('Пушкин А.С.');
insert into author(name) values('Толстой Л.Н.');
insert into author(name) values('Лермонтов М.Ю.');

insert into publisher(name) values('Питер');
insert into publisher(name) values('Берюзовая сосна');
insert into publisher(name) values('ЭЭЭ');

insert into barcode(code) values('858585');
insert into barcode(code) values('858586');
insert into barcode(code) values('858587');
insert into barcode(code) values('858588');
insert into barcode(code) values('858589');
insert into barcode(code) values('858590');

insert into book(name, author_id, publisher_id, barcode_id) values('Собрание сочинений т1', '3', '1', '1');
insert into book(name, author_id, publisher_id, barcode_id) values('Герой нашего времени', '3', '2', '2');
insert into book(name, author_id, publisher_id, barcode_id) values('Война и мир т1', '2', '1', '3');
insert into book(name, author_id, publisher_id, barcode_id) values('Война и мир т2', '2', '2', '4');
insert into book(name, author_id, publisher_id, barcode_id) values('Кавказкий пленник', '1', '1', '5');
insert into book(name, author_id, publisher_id, barcode_id) values('Руслан и Людмила', '1', '3', '6');


select b.name as Имя_книги, bb.name as Автор, bbb.name as Издание, bbbb.code as Штрихкод from book as b join author as bb on b.author_id = bb.id join publisher as bbb on b.publisher_id = bbb.id join barcode as bbbb on b.barcode_id = bbbb.id;