-- INSERT INTO auth_role VALUES (1,'ROLE_ADMIN','This user has ultimate rights for everything');
-- INSERT INTO auth_role VALUES (2,'ROLE_SELLER','This user has admin rights for administrative work');
-- INSERT INTO auth_role VALUES (3,'ROLE_BUYER','This user has access to site, after login - normal user');
--
--- insert into auth_user_role (auth_user_id, auth_role_id) values ('1','1');
-- insert into auth_user_role (auth_user_id, auth_role_id) values ('1','2');
-- insert into auth_user_role (auth_user_id, auth_role_id) values ('1','3');


create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

INSERT INTO Role VALUES (1, 'ADMIN');
INSERT INTO Role VALUES (2, 'SELLER');
INSERT INTO Role VALUES (3, 'DEVELOPER');
INSERT INTO Role VALUES (4, 'BUYER');


insert into user (user_id,name,last_name,email,password,active) values (1,'Ankit','Wasankar','admin@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i',1);
 insert into user_role (user_id, role_id) values ('1','4');
 insert into user_role (user_id, role_id) values ('1','1');
 insert into user_role (user_id, role_id) values ('1','2');