insert into markets(name, code, mainipadress) values ('ММ Первый', '9901', '1.1.1.1');
insert into markets(name, code, mainipadress) values ('ММ Второй', '9902', '2.2.2.2');
insert into markets(name, code, mainipadress) values ('ММ Третий', '9903', '3.3.3.3');
insert into markets(name, code, mainipadress) values ('ММ Четвертый', '9904', '4.4.4.4');
insert into markets(name, code, mainipadress) values ('ММ Пятый', '9905', '5.5.5.5');
insert into markets(name, code, mainipadress) values ('ММ Шестой', '9906', '6.6.6.6');
insert into markets(name, code, mainipadress) values ('ММ Седьмой', '9907', '7.7.7.7');
insert into markets(name, code, mainipadress) values ('ММ Восьмой', '9908', '8.8.8.8');

insert into positions(name) values ('Супервайзер');

insert into employees(name, positions_id) values ('Иванов И.И', '1');
insert into employees(name, positions_id) values ('Петров И.И', '1');
insert into employees(name, positions_id) values ('Кузнецов И.И', '1');

insert into distribution(employees_id, markets_id) values ('1', '1');
insert into distribution(employees_id, markets_id) values ('1', '2');
insert into distribution(employees_id, markets_id) values ('1', '3');
insert into distribution(employees_id, markets_id) values ('2', '4');
insert into distribution(employees_id, markets_id) values ('2', '5');
insert into distribution(employees_id, markets_id) values ('2', '6');
insert into distribution(employees_id, markets_id) values ('3', '7');
insert into distribution(employees_id, markets_id) values ('3', '8');

insert into access_code(code, activated) values ('74125', 'true');
insert into access_code(code, activated) values ('45685', 'true');
insert into access_code(code, activated) values ('35353', 'true');

insert into employees_access_code(employees_id, access_code) values ('1', '1');
insert into employees_access_code(employees_id, access_code) values ('2', '3');
insert into employees_access_code(employees_id, access_code) values ('3', '2');
