CREATE TABLE Person (
person_id INT PRIMARY KEY ,
person_name varchar(20),
person_last varchar(30),
country varchar(30),
vehicle_id int
);

CREATE TABLE Vehicle (
vehicle_id INT PRIMARY KEY,
model varchar(20),
plate_number varchar(30),
country varchar(20)
);

INSERT INTO Person (person_id, person_name, person_last, country, vehicle_id) VALUES (1, 'Juan', 'Chota Corta', 'Honduras', 1);
INSERT INTO Person (person_id, person_name, person_last, country, vehicle_id) VALUES (2, 'Mengueche', 'Alvarez', 'Argentina', 3);
INSERT INTO Person (person_id, person_name, person_last, country, vehicle_id) VALUES (3, 'Paul', 'Vaso', 'Taiwan', 5);
INSERT INTO Person (person_id, person_name, person_last, country, vehicle_id) VALUES (4, 'Benito', 'Tocamela', 'Uruguay', 2);
INSERT INTO Person (person_id, person_name, person_last, country, vehicle_id) VALUES (5, 'Rosa', 'Meltrozo', 'El Lolo', 4);
INSERT INTO Person (person_id, person_name, person_last, country, vehicle_id) VALUES (6, 'Los Centranos', 'Chota Corta', 'Nicaragua', 6);
INSERT INTO Person (person_id, person_name, person_last, country, vehicle_id) VALUES (7, 'John', 'Salchicon', 'Argentina', 8);
INSERT INTO Person (person_id, person_name, person_last, country, vehicle_id) VALUES (8, 'MC', 'Popper', 'Madagascar', 9);
INSERT INTO Person (person_id, person_name, person_last, country, vehicle_id) VALUES (9, 'Jontier', 'Teculie', 'France', 10);
INSERT INTO Person (person_id, person_name, person_last, country, vehicle_id) VALUES (10, 'Polemica', 'Dilemica', 'I dont give a fuck', 7);

INSERT INTO Vehicle (vehicle_id, model, plate_number, country) VALUES (1, 'Supra', 'AP1320', 'Japan');
INSERT INTO Vehicle (vehicle_id, model, plate_number, country) VALUES (2, 'Yaris', 'PBC666', 'Singapore');
INSERT INTO Vehicle (vehicle_id, model, plate_number, country) VALUES (3, 'Lamborghini', 'AB1542', 'Honduras');
INSERT INTO Vehicle (vehicle_id, model, plate_number, country) VALUES (4, 'Mclaren', 'TY9090', 'Costa Rica');
INSERT INTO Vehicle (vehicle_id, model, plate_number, country) VALUES (5, 'Aston Martin', 'JP1532', 'El Chizito');
INSERT INTO Vehicle (vehicle_id, model, plate_number, country) VALUES (6, 'Compadre', 'AP1321', 'Honduras');
INSERT INTO Vehicle (vehicle_id, model, plate_number, country) VALUES (7, 'Rio', 'PBC665', 'Nicaragua');
INSERT INTO Vehicle (vehicle_id, model, plate_number, country) VALUES (8, 'Bentley', 'PB1542', 'Honduras');
INSERT INTO Vehicle (vehicle_id, model, plate_number, country) VALUES (9, 'Mclaren', 'TY9290', 'Rio San Juan');
INSERT INTO Vehicle (vehicle_id, model, plate_number, country) VALUES (10, 'CheryQQ', 'JP1537', 'Bolivia');
