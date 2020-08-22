DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users(
	id IDENTITY PRIMARY KEY,
	name VARCHAR(30),
	date DATE,
	time TIME,
	timedate TIMESTAMP);

INSERT INTO users (id, name, date, time, timedate) VALUES (DEFAULT, 'Roman', '2020-05-05', '19:30', '2020-05-05 19:30');
INSERT INTO users (id, name, date, time, timedate) VALUES (DEFAULT, 'Vika', '2020-06-06', '04:30', '2020-05-05 19:30');
INSERT INTO users (id, name, date, time, timedate) VALUES (DEFAULT, 'Tina', '2020-07-07', '12:30', '2020-05-05 19:30');

