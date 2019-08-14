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
INSERT INTO `users`(user_id, enabled, email, password) VALUES (3, true,'da@com', '$2a$10$mvQhJwYKcKjycRDKy8bPUO7Czn036KOGxUIHuTF9WpXxjHcbmZVEu');
INSERT INTO `users`(user_id, enabled, email, password) VALUES (6, true,'danizjob@gmail.com','$2a$10$/I1S.hrojwchdOJ9xO1bBu.GZHxchpi0sDOb5qmpXNMgoQ7Q05VHe');


INSERT INTO `user_role`(user_id, role_id) VALUES (1, 1);
-- INSERT INTO `user_role`(user_id, role_id) VALUES (1, 2);
-- INSERT INTO `user_role`(user_id, role_id) VALUES (1, 3);




INSERT INTO `address`(id,city,state,street,zipcode) VALUES (2,'fairfield','IO','205 E Hampstead ave	','52556');

 INSERT INTO `address`(id,city,state,street,zipcode) VALUES (5,'Sanleandro','California','144Th Apt201','98168');

INSERT INTO `seller`(id,name,security_answer,security_question,address_id,credential_user_id) VALUES (1,'Daniel A Zuemui','asmara','What is Your old Phone Number?',2,3);

 INSERT INTO `seller`(id,name,security_answer,security_question,address_id,credential_user_id) VALUES (4,'Dawit','jack','What is your Pet Name?	',5,6);

INSERT INTO `user_role`(user_id, role_id) VALUES (3, 2);
INSERT INTO `user_role`(user_id, role_id) VALUES (6, 2);


INSERT INTO `users`(user_id, enabled, email, password) VALUES (8, true,'da16ni19@gmail.com	','$2a$10$0TMkVFHEhqlYiYf9WtjSEu9J/JNPHjWq8nYttSd81jLB0I5DVlxWS');
INSERT INTO `users`(user_id, enabled, email, password) VALUES (10, true,'dzuemui@mum.edu','$2a$10$.outbZpsuhjFme0TADXFX.2Fs/O0EQ0OFzanZmWDyaGRVGxjoizf6');

INSERT INTO `buyer`(id,first_name,gender,last_name,points,security_answer,security_question,credential_user_id,payment_id) VALUES (7,'Daniel','MALE','Zuemui',0,'Danny','What is your nick name?',8,null );
INSERT INTO `buyer`(id,first_name,gender,last_name,points,security_answer,security_question,credential_user_id,payment_id) VALUES (9,'Luwam','FEMALE','Milikias',0,'couraza','Who is your favourite professor?	',10,null );




INSERT INTO `user_role`(user_id, role_id) VALUES (8, 3);
INSERT INTO `user_role`(user_id, role_id) VALUES (10, 3);

