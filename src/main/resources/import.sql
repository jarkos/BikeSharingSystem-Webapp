INSERT INTO webapp.user (version,email,enabled,locked,password,username) VALUES (0, 'admin@test.com', 1, 0, '123456', 'admin');
INSERT INTO webapp.user (version,email,enabled,locked,password,username) VALUES (0, 'jarkos@test.com', 1, 0, 'jarkos', 'jarkos');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '0', '1');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '1', '1');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '2', '1');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '0', '2');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '1', '2');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '2', '2');
