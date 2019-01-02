DROP DATABASE IF EXISTS appunti;
CREATE DATABASE appunti;
USE appunti;



CREATE TABLE IF NOT EXISTS utente(
Username Varchar(50) NOT NULL,
Nome Varchar(20) NOT NULL,
Cognome	Varchar(20)	NOT NULL,
Email Varchar(50) NOT NULL,
Paswd Varchar(50) NOT NULL,
TipoUtente boolean NOT NULL,
id_utente boolean NOT NULL,

PRIMARY KEY (Username)
);

CREATE TABLE IF NOT EXISTS tipo_utente(
TipoUtente boolean NOT NULL,
Nome Varchar(20),

PRIMARY KEY (TipoUtente)
);

CREATE TABLE IF NOT EXISTS richiesta(
Id INT NOT NULL,
Foto Varchar(50) NOT NULL,
Titolo Varchar(50) NOT NULL,
Pagine INT NOT NULL,
Universita Varchar(50) NOT NULL,
Descrizione Varchar(50) NOT NULL,
Tipo Varchar(50) NOT NULL,
Documento Varchar(50) NOT NULL,
Username Varchar(50) NOT NULL,
Stato boolean NOT NULL,

PRIMARY KEY (Id),
FOREIGN KEY(Username) REFERENCES utente(Username)
);

CREATE TABLE IF NOT EXISTS carrello(
Identificativo INT NOT NULL,
Username Varchar(50) NOT NULL,

PRIMARY KEY (Identificativo),
FOREIGN KEY(Username) REFERENCES utente(Username)
);

CREATE TABLE IF NOT EXISTS recensione(
ID INT NOT NULL,
Descrizione Varchar(50) NOT NULL,
Username Varchar(50) NOT NULL,
flag boolean NOT NULL,

PRIMARY KEY (ID),
FOREIGN KEY(Username) REFERENCES utente(Username)
);


CREATE TABLE IF NOT EXISTS documento(
Codice INT NOT NULL,
Foto Varchar(50) NOT NULL,
Titolo Varchar(50) NOT NULL,
Pagine INT NOT NULL,
Universita Varchar(50) NOT NULL,
Descrizione Varchar(50) NOT NULL,
Prezzo decimal(6,2) NOT NULL,
Tipo Varchar(50) NOT NULL,
ID INT NOT NULL,
flag boolean NOT NULL,

PRIMARY KEY (Codice),
FOREIGN KEY(ID) REFERENCES recensione(ID)
);

CREATE TABLE IF NOT EXISTS materia(
Nome Varchar(50) NOT NULL,
Codice INT NOT NULL,
flag boolean NOT NULL,

PRIMARY KEY (Nome),
FOREIGN KEY(Codice) REFERENCES documento(Codice)
);


CREATE TABLE IF NOT EXISTS ordine(
SerialId INT NOT NULL,
Data INT NOT NULL,
Ora INT NOT NULL,
Indirizzo Varchar(50) NOT NULL,
MetodoPagamento Varchar(50)	NOT NULL,
CartaCredito Char(12) NOT NULL,
Username Varchar(50) NOT NULL,

PRIMARY KEY (SerialId),
FOREIGN KEY(Username) REFERENCES utente(Username)
);

CREATE TABLE IF NOT EXISTS effettuata(
Id INT NOT NULL,
Username Varchar(50) NOT NULL,

PRIMARY KEY (Id,Username),
FOREIGN KEY(Id) REFERENCES richiesta(Id),
FOREIGN KEY(Username) REFERENCES utente(Username)
);

CREATE TABLE IF NOT EXISTS contiene(
Identificativo INT NOT NULL,
Codice INT NOT NULL,

PRIMARY KEY (Identificativo,Codice),
FOREIGN KEY(Identificativo) REFERENCES carrello(Identificativo),
FOREIGN KEY(Codice) REFERENCES documento(Codice)
);

CREATE TABLE IF NOT EXISTS composto_da(
SerialId INT NOT NULL,
Codice INT NOT NULL,

PRIMARY KEY (SerialId,Codice),
FOREIGN KEY(SerialId) REFERENCES ordine(SerialId),
FOREIGN KEY(Codice) REFERENCES documento(Codice)
);

/*Utente*/
INSERT INTO utente(Username,Nome,Cognome,Email,Paswd,TipoUtente,id_utente) 
VALUES('roccold77','Rocco','Lo Duca','roccoloduca77@hotmail.it','rlduca1977',0,0);
INSERT INTO utente(Username,Nome,Cognome,Email,Paswd,TipoUtente,id_utente) 
VALUES('guepa45', 'Guendalina', 'Palerma','guepalerma45@gmail.com', 'guendalina45',0,0);
INSERT INTO utente(Username,Nome,Cognome,Email,Paswd,TipoUtente,id_utente) 
VALUES('ViviCos11', 'Viviana', 'Costi', 'vivianacosti11@libero.it','VivIana11',0,0);
INSERT INTO utente(Username,Nome,Cognome,Email,Paswd,TipoUtente,id_utente) 
VALUES('FiorenzNap99', 'Fiorenzo', 'Napolitano', 'fiorenzonap99@hotmail.it', 'fiorenapo111',0,0);
INSERT INTO utente(Username,Nome,Cognome,Email,Paswd,TipoUtente,id_utente) 
VALUES('SimoEndri34', 'Simonetta', 'Endrizzi', 'simoendri34@libero.it', 'simonettaend22',0,0);
INSERT INTO utente(Username,Nome,Cognome,Email,Paswd,TipoUtente,id_utente) 
VALUES('franpa96','Francesco', 'Parisi','f.parisi896@libero.it', 'admin1',1,0);

/*TipoUtente*/
INSERT INTO tipo_utente(TipoUtente,Nome)
VALUES(0,'Studente');
INSERT INTO tipo_utente(TipoUtente,Nome)
VALUES(1,'Gestore');

INSERT INTO richiesta(Id,Foto,Titolo,Pagine,Universita,Descrizione,Tipo,Documento,Username,Accettata)
VALUES(1000,'ffff','Prova',100,'Universita degli studi di Salerno','prova prova prova','Appunto','pppp','rlduca1977','1');

set foreign_key_checks=0;

SELECT s.Username,s.Nome,s.Cognome,s.Email
FROM utente s
WHERE s.TipoUtente=1;