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
)
