create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

INSERT INTO `role` VALUES (1,'ROLE_ADMIN');
INSERT INTO `role` VALUES (2,'ROLE_SELLER');
INSERT INTO `role` VALUES (3,'ROLE_BUYER');

INSERT INTO `users`(user_id, enabled, email, password) VALUES (1, true,'admin', '$2a$10$GrRA7FvSa2ewQhJSsYu8gu493UDQ3hDyxDtwTqOPVLmYHZ6G.DHBu');
INSERT INTO `user_role`(user_id, role_id) VALUES (1, 1);
INSERT INTO `user_role`(user_id, role_id) VALUES (1, 2);
INSERT INTO `user_role`(user_id, role_id) VALUES (1, 3);
