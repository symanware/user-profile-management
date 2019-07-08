INSERT INTO role (id, role_type, description) VALUES (1, 'USER', 'User - Has no admin rights');
INSERT INTO role (id, role_type, description) VALUES (2, 'ADMIN', 'Admin - Has permission to perform admin tasks');

-- USER
-- non-encrypted password: jwtpass
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (1, 'John', 'Doe', '$2a$10$qtH0F1m488673KwgAfFXEOWxsoZSeHqqlB/8BTt3a6gsI5c2mdlfe', 'john.doe');
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (2, 'Admin', 'Admin', '$2a$10$qtH0F1m488673KwgAfFXEOWxsoZSeHqqlB/8BTt3a6gsI5c2mdlfe', 'admin.admin');

INSERT INTO user_role(user_id, role_id) VALUES (1,1);
INSERT INTO user_role(user_id, role_id) VALUES (2,1);
INSERT INTO user_role(user_id, role_id) VALUES (2,2);

INSERT INTO address (id, type, line1, line2, city, state, zipcode, email) VALUES (1, 'HOME', '123', 'BERTH STREET', 'MELBOURNE', 'VIC', '3333', 'john@gmail.com');
INSERT INTO address (id, type, line1, line2, city, state, zipcode, email) VALUES (2, 'BUSINESS', '222', 'POATH STREET', 'MELBOURNE', 'VIC', '3333', 'admin@gmail.com');

INSERT INTO user_address (user_id, address_id) VALUES (1,1);
INSERT INTO user_address (user_id, address_id) VALUES (2,2);
