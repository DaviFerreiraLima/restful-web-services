insert into user_details(id,name,birth_date)
values (1001,'Ranga',current_date()),
 (1002,'Joicy',current_date()),
 (1003,'Davi',current_date());

insert into post(id,description,user_id) values (10001,'hi guys',1001);
insert into post(id,description,user_id) values (10002,'I want to learn aws',1001);