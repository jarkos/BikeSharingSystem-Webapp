INSERT INTO webapp.user (email,enabled,locked,password,username) VALUES ('admin@test.com', 1, 0, '123456', 'admin');
INSERT INTO webapp.user (email,enabled,locked,password,username) VALUES ('jarkos@test.com', 1, 0, 'jarkos', 'jarkos');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '0', '1');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '1', '1');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '2', '1');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '0', '2');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '1', '2');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '2', '2');
-- INSERT INTO webapp.bike (version,manufacturer,enabled,model,serial_number,bike_status) VALUES (0, 'Kross', 1, 'CityB200', 'XNXCM1231432', 'TO_BORROW');