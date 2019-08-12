-- INSERT INTO `role` VALUES (1,'ROLE_ADMIN','This user has ultimate rights for everything');
-- INSERT INTO `role` VALUES (2,'ROLE_SELLER','This user has admin rights for administrative work');
-- INSERT INTO `role` VALUES (3,'ROLE_BUYER','This user has access to site, after login - normal user');
--
-- insert into `users` (auth_user_id,first_name,last_name,email,password,status) values (1,'Ankit','Wasankar','admin@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i','VERIFIED');
-- insert into `user_role` (auth_user_id, auth_role_id) values ('1','1');
-- insert into `user_role` (auth_user_id, auth_role_id) values ('1','2');
-- insert into `user_role` (auth_user_id, auth_role_id) values ('1','3');

INSERT INTO `role` VALUES (1,'ROLE_ADMIN');
INSERT INTO `role` VALUES (2,'ROLE_SELLER');
INSERT INTO `role` VALUES (3,'ROLE_BUYER');

INSERT INTO `users`(user_id, enabled, email, password) VALUES (1, true,'dmebrahtu@mum.edu', '$2a$10$Vx/y9T1YimhX58ZcWiCH2.kLcO9X9J1eQXmilfCsNHfzZMtUfjrt2');
INSERT INTO `user_role`(user_id, role_id) VALUES (1, 1);
INSERT INTO `user_role`(user_id, role_id) VALUES (1, 2);
INSERT INTO `user_role`(user_id, role_id) VALUES (1, 3);
