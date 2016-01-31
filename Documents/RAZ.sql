#Creation de la base

create table Utilisateurs(
	nom varchar(50),
	prenom varchar(50),
	mail varchar(80),
	AnneDiplomation int(4),
	tel int(15),
	mdp varchar(50),
	constraint pk_utlisateur PRIMARY KEY(mail)
	#constraint ck_athletes check (type in ('F','M','A')),
);

create table Competences(
	description varchar(200),
	constraint pk_competences PRIMARY KEY(description)
);

create table Acquerir(
	mail varchar(80),
	description varchar(200),
	constraint pk_acquerir PRIMARY KEY(mail, description),
	constraint fk_acquerir_utlisateur foreign key(mail) references Utilisateurs(mail),
	constraint fk_acquerir_competences foreign key(description) references Competences(description)
);


INSERT INTO Utilisateurs values
	('Vaurigaud', 'Jordan', 'jojo@gmail.com', 2016, "0689784556"	),
	('Hernandez', 'Quentin', 'kent@yahoo.fr', 2018, "0689784556"	),
	('Iungmann', 'Victor', 'vic@gmail.com', 2017, "0689784556"	);




INSERT INTO Competences(description) values
	('Informatique'),
	('Repassage'),
	('BDD'),
	('Maths'),
	('Physique'),
	('Chimie'),
	('Gestion');

	INSERT INTO Acquerir values
		('kent@yahoo.fr','Repassage'),
		('jojo@gmail.com','Maths'),
		('jojo@gmail.com','Chimie'),
		('jojo@gmail.com','Physique'),
		('jojo@gmail.com','Gestion'),
		('vic@gmail.com','BDD'),
		('vic@gmail.com','Maths'),
		('vic@gmail.com','Informatique');


