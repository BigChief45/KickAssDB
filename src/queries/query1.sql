CREATE TABLE Person (
person_id INT PRIMARY KEY,
person_name VARCHAR(20),
person_last VARCHAR(30),
country VARCHAR(20)
);

CREATE TABLE Vehicle (
vehicle_id INT PRIMARY KEY,
plate_number VARCHAR(20),
model VARCHAR(30),
country VARCHAR(20)
);

INSERT INTO Person (person_id, person_name, person_last, country) VALUES (1, 'Jaime', 'Alvarez', 'Honduras');
INSERT INTO Person (person_id, person_name, person_last, country) VALUES (2, 'John', 'Salchichon', 'Gambia');
INSERT INTO Person (person_id, person_name, person_last, country) VALUES (3, 'Paul', 'Vazo', 'Cambodia');

INSERT INTO Vehicle VALUES (1, 'AS231', 'Camry', 'Singapore');
INSERT INTO Vehicle VALUES (2, 'DG334', 'Yaris', 'Taiwan');
INSERT INTO Vehicle VALUES (3, 'ZXG334', 'Montero', 'Australia');
INSERT INTO Vehicle VALUES (4, 'HGH43', 'Supra', 'China')