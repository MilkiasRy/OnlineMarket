create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

-- ADDRESS DUMMY DATA

INSERT INTO `address`(id, street, city, state, zipcode) VALUES (1, '205 E Hempstead Ave, Apt 3','Fairfield', 'IA', '52556');


INSERT INTO `role` VALUES (1,'ROLE_ADMIN');
INSERT INTO `role` VALUES (2,'ROLE_SELLER');
INSERT INTO `role` VALUES (3,'ROLE_BUYER');

INSERT INTO `users`(user_id, enabled, email, password) VALUES (1, true,'admin', '$2a$10$GrRA7FvSa2ewQhJSsYu8gu493UDQ3hDyxDtwTqOPVLmYHZ6G.DHBu');
INSERT INTO `users`(user_id, enabled, email, password) VALUES (2, true,'seller', '$2a$10$LRlwZ4lQnp6uihXsioz7t.zn06ju9iA7JukEltvoh2xHCn.4sVQUW');
INSERT INTO `users`(user_id, enabled, email, password) VALUES (3, true,'buyer', '$2a$10$/0P756jcpXkxc2vgCAPIwuse5CU/dFFbF9O9B3rUbzhwBz6Xwguuu');

INSERT INTO `user_role`(user_id, role_id) VALUES (1, 1);
INSERT INTO `user_role`(user_id, role_id) VALUES (2, 2);
INSERT INTO `user_role`(user_id, role_id) VALUES (3, 3);

-- SELLER DUMMY DATA
INSERT INTO `seller`(id, name, security_question, security_answer, address_id, credential_user_id) VALUES (1, 'Nike','What is Your old Phone Number?', '123456', 1, 2);


-- BUYER DUMMY DATA
INSERT INTO `buyer`(id, first_name, last_name, gender, points, payment_id, security_question, security_answer, credential_user_id) VALUES (1, 'John','Doe', 'Male', 0, null,'Who is your favourite professor?', 'tina', 3);
