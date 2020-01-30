DROP DATABASE MyGym;
CREATE DATABASE MyGym;
USE MyGym;

CREATE TABLE Trainer(
	TrainerID VARCHAR(5) NOT NULL,
	TrainerFirstName VARCHAR(16) NOT NULL,
	TrainerLastName VARCHAR(16) NOT NULL,
	TrainerPhoneNo VARCHAR(8) NOT NULL,
	Gender ENUM('M','F') NOT NULL,
	PRIMARY KEY (TrainerID)
);
	
CREATE TABLE Specialism(
	SpecialismID VARCHAR(5) NOT NULL,
	SpecialismName VARCHAR(40) NOT NULL,
	SpecialismFee VARCHAR(4) NOT NULL,
	PRIMARY KEY (SpecialismID)
);

CREATE TABLE Customer(
	CustomerID VARCHAR(5) NOT NULL,
	CustomerFirstName VARCHAR(16) NOT NULL,
	CustomerLastName VARCHAR(16) NOT NULL,
	CustomerGender ENUM('M','F') NOT NULL,
	CustomerDOB DATE NOT NULL,
	CustomerAddress VARCHAR(64),
	CustomerPhoneNo VARCHAR(8) NOT NULL,
	PRIMARY KEY (CustomerID)
);

CREATE TABLE Focus(
	FocusID VARCHAR(7) NOT NULL,
	TrainerID VARCHAR(5) NOT NULL,
	SpecialismID VARCHAR(5) NOT NULL,
	PRIMARY KEY (FocusID),
	FOREIGN KEY (TrainerID) REFERENCES Trainer(TrainerID),
	FOREIGN KEY (SpecialismID) REFERENCES Specialism(SpecialismID)
);

CREATE TABLE Booking(
	BookingID VARCHAR(5) NOT NULL,
	CustomerID VARCHAR(5) NOT NULL,
	TrainerID VARCHAR(5) NOT NULL,
	SpecialismID VARCHAR(5) NOT NULL,
	TimeSlot VARCHAR(12) NOT NULL,
	DateBooked DATE NOT NULL,
	PRIMARY KEY (BookingID),
	FOREIGN KEY (TrainerID) REFERENCES Trainer(TrainerID),
	FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
	FOREIGN KEY (SpecialismID) REFERENCES Specialism(SpecialismID)
);

INSERT INTO Trainer VALUES
('FPB22', 'Pelagiya', 'Bergmann', 54629922, 'F'),
('FSE89', 'Silvia', 'Esteves', 57479689, 'F'),
('FPJ17', 'Priscilla', 'Johnsen', 54246317, 'F'),
('MLS07', 'Leon', 'Starosta', 53450507, 'M'),
('MOM75', 'Otto', 'MacBay', 54864575, 'M'),
('MVS67', 'Vasil', 'Shaw', 55122767, 'M');

INSERT INTO Specialism VALUES
('WEILO', 'Weight loss', 800),
('MUSGA', 'Muscle gain', 400),
('CARFI', 'Cardiovascular fitness', 200),
('ENSTE', 'Energy, stamina & endurance', 400),
('SPOPE', 'Sports performance', 500),
('MUSEF', 'Muscle strength, endurance & flexibilty', 600),
('COORD', 'Coordination', 200),
('IMMUN', 'Immunity', 300),
('STRAN', 'Stress & anxiety', 400),
('LIBID', 'Libido', 300),
('ATTRA', 'Attractiveness', 700);

INSERT INTO Focus VALUES
('GAFSE89', 'FSE89', 'MUSGA'),
('TEMLS07', 'MLS07', 'ENSTE'),
('EFMOM75', 'MOM75', 'MUSEF'),
('LOFSE89', 'FSE89', 'WEILO'),
('LOFPB22', 'FPB22', 'WEILO'),
('RDMUS67', 'MVS67', 'COORD'),
('GAMLS07', 'MLS07', 'MUSGA'),
('RAFPJ17', 'FPJ17', 'ATTRA'),
('FIMUS67', 'MVS67', 'CARFI');

INSERT INTO Customer VALUES
('FLW35', 'Lacie', 'Watkins', 'F', '2001-08-01', 'Curepipe', 54840435),
('FAB68', 'Alfie-Jay', 'Burt', 'F', '2002-10-30', 'Flic en Flac', 57818268),
('FLP55', 'Layla-Mae', 'Pitt', 'F', '2000-11-25', 'Rose-Hill', 57818355),
('MFM12', 'Filip', 'Mitchell', 'M', '1999-05-11', 'Mahebourg', 54840112),
('MBR01', 'Bernard', 'Rocha', 'M', '2002-06-10', 'Vacoas', 54188401),
('MOD12', 'Olivier', 'Dixon', 'M', '1998-04-22', 'Phoenix', 58401812),
('FDR48', 'Darla', 'Ray', 'F', '1999-04-12', 'Port-Louis', 54185148);

INSERT INTO Booking VALUES
('DBGJ2', 'FLW35', 'FPJ17', 'ATTRA', '18:00-19:30', '2020-03-10'),
('DG2VF', 'FAB68', 'FSE89', 'WEILO', '19:00-20:00', '2020-02-24'),
('ADSFG', 'FDR48', 'MOM75', 'MUSEF', '20:00-21:00', '2020-04-30'),
('GEAGE', 'MBR01', 'MOM75', 'MUSEF', '18:30-19:30', '2020-06-12'),
('JNVDG', 'MOD12', 'MLS07', 'ENSTE', '15:15-16:30', '2020-05-15'),
('7DG7A', 'MFM12', 'MVS67', 'CARFI', '19:00-20:45', '2020-10-01');
