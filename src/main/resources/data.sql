INSERT INTO user_account (user_name, first_name, last_name, email, password) VALUES ('crazyk', 'John', 'Smith', 'abc@abc.com', 'qwerty123');
INSERT INTO user_account (user_name, first_name, last_name, email, password) VALUES ('jimbo', 'Karl', 'Jackson', 'asd@abc.com', 'qqq111');

INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_USER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
