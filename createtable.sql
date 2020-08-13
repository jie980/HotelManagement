
CREATE TABLE Department(
	did SERIAL PRIMARY KEY,
	dname VARCHAR(30) NOT NULL);
	
CREATE TABLE Employee(
	eid SERIAL PRIMARY KEY,
	email VARCHAR(30),
	ename VARCHAR(30),
	gender INT,
	did INT,
	FOREIGN KEY (did) REFERENCES Department);
	
CREATE TABLE Room(
rid SERIAL PRIMARY KEY,
rtype VARCHAR(20) NOT NULL,
roomStatus VARCHAR(20) NOT NULL,
roomNumber INTEGER UNIQUE
);

ALTER TABLE Room ADD roomNumber INTEGER UNIQUE;
ALTER TABLE Room DROP COLUMN roomNumber;

CREATE TABLE RoomRemark(
rrid SERIAL PRIMARY KEY,
topic VARCHAR(30) NOT NULL,
contentDetail VARCHAR(200),
createDate TIMESTAMP,
rid INTEGER,
FOREIGN KEY(rid) REFERENCES Room
);

CREATE TABLE Customer(
cid SERIAL PRIMARY KEY,
phoneNum BIGINT UNIQUE,
email VARCHAR(30) UNIQUE,
cname VARCHAR(30))
;

CREATE TABLE Reservation(
reid SERIAL PRIMARY KEY,
startDate DATE NOT NULL,
endDate DATE NOT NULL,
reservationStatus VARCHAR(10) NOT NULL,
cid INTEGER,
FOREIGN KEY(cid) REFERENCES Customer
);


CREATE TABLE RoomReservation(
rid INTEGER NOT NULL,
reid INTEGER NOT NULL,
PRIMARY KEY(rid, reid),
FOREIGN KEY(rid) 
    REFERENCES Room,
FOREIGN KEY(reid) 
    REFERENCES Reservation
);


CREATE TABLE Administrator(
aid SERIAL PRIMARY KEY,
username VARCHAR(30) NOT NULL,
email VARCHAR(30) NOT NULL,
pwd VARCHAR(100) NOT NULL);


