INSERT INTO auth_role VALUES (1,'ROLE_ADMIN','This user has ultimate rights for everything');
INSERT INTO auth_role VALUES (2,'ROLE_SELLER','This user has admin rights for administrative work');
INSERT INTO auth_role VALUES (3,'ROLE_BUYER','This user has access to site, after login - normal user');

insert into auth_user (auth_user_id,first_name,last_name,email,password,status) values (1,'Ankit','Wasankar','admin@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i','VERIFIED');
insert into auth_user_role (auth_user_id, auth_role_id) values ('1','1');
insert into auth_user_role (auth_user_id, auth_role_id) values ('1','2');
insert into auth_user_role (auth_user_id, auth_role_id) values ('1','3');