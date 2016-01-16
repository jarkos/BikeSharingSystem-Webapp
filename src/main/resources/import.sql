-- ALTER TABLE webapp.user
-- CHANGE COLUMN account_balance account_balance DECIMAL(10) NOT NULL ;

INSERT INTO webapp.user (email,enabled,locked,password,username,account_balance,bike_id,full_name,address,verified) VALUES ('admin@test.com', 1, 0, '123456', 'admin',10,NULL,'asdasda', 'asfasfasfa',1);
INSERT INTO webapp.user (email,enabled,locked,password,username,account_balance,bike_id,full_name,address,verified) VALUES ('jarkos@test.com', 1, 0, 'jarkos', 'jarkos',10,NULL,'asdasda', 'asfasfasfa',1);
INSERT INTO webapp.user (email,enabled,locked,password,username,account_balance,full_name,address,verified) VALUES ('jarkos2@test.com', 1, 0, 'jarkos2', 'jarkos2',10,'asdasda', 'asfasfasfa',1);
INSERT INTO webapp.user_role (role,user_id) VALUES ( '0', '1');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '1', '1');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '2', '1');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '2', '2');
INSERT INTO webapp.user_role (role,user_id) VALUES ( '0', '3');

INSERT INTO webapp.bike (manufacturer,enabled,model,serial_number, station_id, status) VALUES ('Kross', 1, 'CityB200', 'XNXCM1231432', NULL, 'NEW' );