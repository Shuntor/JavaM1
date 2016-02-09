#Creation de la base

create table Utilisateurs(
	nom varchar(50),
	prenom varchar(50),
	mail varchar(80),
	AnneDiplomation Varchar(4),
	tel Varchar(15),
	mdp varchar(50),
	showTel Varchar(1),
	showAnneDiplomation Varchar(1),
	showCompetences Varchar(1),
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








