INSERT INTO user_data (user_id, first_name, last_name, password, email, phone_number, address_line1, address_line2, address_line3)
       VALUES (100, 'Default', 'user', '{bcrypt}$2a$12$8HH4cXoQ29ynPLKrdU2W9uwfrgPGrWsSm3DNkrQwy/pfOSm7KNE5O', 'default@mail.com', '1234567890','test','test','test');


INSERT INTO role(role_id, user_id, description, role_names)
       VALUES(101, 100, 'Default role','ADMIN');
