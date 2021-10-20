insert into role(name) values('Прапор');
insert into role(name) values('Стажер');
insert into role(name) values('Продавец');

insert into users(name, role_id) values('Попов', '1');
insert into users(name, role_id) values('Петров', '3');
insert into users(name, role_id) values('Кузнецов', '3');
insert into users(name, role_id) values('Иванов', '3');
insert into users(name, role_id) values('Васильев', '2');

insert into rules(name) values ('Выдавать инвентарь');
insert into rules(name) values ('Продавать');
insert into rules(name) values ('Считать');
insert into rules(name) values ('Печатать');
insert into rules(name) values ('Бездельничать');

insert into role_rules(role_id, rules_id) values ('1', '1');
insert into role_rules(role_id, rules_id) values ('1', '4');
insert into role_rules(role_id, rules_id) values ('1', '5');
insert into role_rules(role_id, rules_id) values ('2', '5');
insert into role_rules(role_id, rules_id) values ('3', '2');
insert into role_rules(role_id, rules_id) values ('3', '3');
insert into role_rules(role_id, rules_id) values ('3', '4');
insert into role_rules(role_id, rules_id) values ('3', '5');

insert into category(name) values ('1');
insert into category(name) values ('2');
insert into category(name) values ('3');

insert into state(name) values('Новая');
insert into state(name) values('В работе');
insert into state(name) values('Завершена');

insert into item(name, users_id, category_id, state_id) values('Нужно ГСМ', '1', '2', '1');
insert into item(name, users_id, category_id, state_id) values('Посчитать товар на полке', '3', '3', '1');
insert into item(name, users_id, category_id, state_id) values('Напечатать номера полок', '2', '3', '3');
insert into item(name, users_id, category_id, state_id) values('Заняться чем-нибудь', '5', '2', '2');

insert into comments(text, item_id) values('ГСМа нет', '1');
insert into comments(text, item_id) values('Считаем', '2');
insert into comments(text, item_id) values('Плохо', '1');
insert into comments(text, item_id) values('Выполнено на отлично', '4');

insert into attachs(path, item_id) values('Вложение.json', '1');
insert into attachs(path, item_id) values('Еще вложение.json', '2');
insert into attachs(path, item_id) values('Что-нибудь', '4');

