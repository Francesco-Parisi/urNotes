DROP DATABASE IF EXISTS appunti;
CREATE DATABASE appunti;
USE appunti;



CREATE TABLE IF NOT EXISTS utenti(
id_utente int auto_increment unique,
username varchar(50) NOT NULL,
nome varchar(20) NOT NULL,
cognome	varchar(20)	NOT NULL,
email varchar(50) NOT NULL,
paswd varchar(50) NOT NULL,
tipo_utente int NOT NULL,
attivo int NOT NULL,

PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS tipo_utenti(
tipo_utente int NOT NULL,
nome varchar(20),

PRIMARY KEY (tipo_utente)
);


CREATE TABLE IF NOT EXISTS richieste(
id_richiesta int auto_increment NOT NULL,
titolo varchar(100) NOT NULL,
pagine int NOT NULL,
nome_materia varchar(50) NOT NULL, 
universita varchar(50) NOT NULL,
descrizione varchar(250) NOT NULL,
tipo varchar(50) NOT NULL,
data_richiesta date DEFAULT NULL,
id_utente int NOT NULL,
attivo tinyint(1) DEFAULT NULL,

PRIMARY KEY (id_richiesta)
);

CREATE TABLE IF NOT EXISTS documenti(
codice int auto_increment NOT NULL,
titolo varchar(100) NOT NULL,
pagine int NOT NULL,
universita varchar(100) NOT NULL,
nome_materia varchar(50) NOT NULL, 
descrizione varchar(250) NOT NULL,
prezzo float NOT NULL,
tipo varchar(50) NOT NULL,
flag int NOT NULL,

PRIMARY KEY (codice)
);

CREATE TABLE IF NOT EXISTS recensioni(
id_recensione int auto_increment NOT NULL,
descrizione varchar(250) NOT NULL,
username varchar(50) NOT NULL,
codice int(11) DEFAULT NULL,
flag int NOT NULL,

PRIMARY KEY (id_recensione)
);


CREATE TABLE IF NOT EXISTS materie(
nome varchar(50) NOT NULL,
codice int auto_increment unique NOT NULL,
flag int NOT NULL,

PRIMARY KEY (nome)
);

CREATE TABLE IF NOT EXISTS ordini(
serial_id int  NOT NULL AUTO_INCREMENT,
id_vettore int(11) DEFAULT NULL,
id_metodo int(11) DEFAULT NULL,
id_utente int DEFAULT NULL,
id_indirizzo int(11) DEFAULT NULL,
totale_documenti float DEFAULT NULL,
totale_spedizione float DEFAULT NULL,
totale_ordine float DEFAULT NULL,	
data_ordine datetime DEFAULT NULL,
attivo int DEFAULT NULL,

PRIMARY KEY (serial_id)
);


CREATE TABLE IF NOT EXISTS ordini_vettori(
id_vettore int(11) NOT NULL AUTO_INCREMENT,
nome varchar(45) DEFAULT NULL,
descrizione varchar(45) DEFAULT NULL,
costo float DEFAULT NULL,
contrassegno tinyint(1) DEFAULT NULL,
attivo tinyint(1) DEFAULT NULL,

PRIMARY KEY (id_vettore)
);

CREATE TABLE ordini_metodi_pagamento (
id_metodo int(11) NOT NULL AUTO_INCREMENT,
nome varchar(45) DEFAULT NULL,
descrizione varchar(45) DEFAULT NULL,
in_contanti tinyint(1) DEFAULT NULL,
attivo tinyint(1) DEFAULT NULL,

PRIMARY KEY (id_metodo)
);

CREATE TABLE IF NOT EXISTS ordini_documenti(
id_ordine int(11) NOT NULL AUTO_INCREMENT,
serial_id int(11) DEFAULT NULL,
codice int(11) DEFAULT NULL,
titolo varchar(100) DEFAULT NULL,
quantita int(11) DEFAULT NULL,
prezzo_documenti float DEFAULT NULL,
prezzo_totale float DEFAULT NULL,
nome_materia varchar(50) DEFAULT NULL,
attivo int NOT NULL,

PRIMARY KEY (id_ordine)
);

CREATE TABLE indirizzi (
id_indirizzo int(11) NOT NULL AUTO_INCREMENT,
nome varchar(45) DEFAULT NULL,
cognome varchar(45) DEFAULT NULL,
indirizzo varchar(200) DEFAULT NULL,
cap int(11) DEFAULT NULL,
citta varchar(50) DEFAULT NULL,
provincia varchar(50) DEFAULT NULL,
telefono varchar(10) DEFAULT NULL,
cellulare varchar(10) DEFAULT NULL,
id_utente int(11) DEFAULT NULL,
attivo tinyint(1) DEFAULT 0,
  
PRIMARY KEY (id_indirizzo)
);

CREATE TABLE IF NOT EXISTS documenti_immagini(
id_immagine int(11) NOT NULL AUTO_INCREMENT,
codice int(11) DEFAULT NULL,
filename varchar(100) DEFAULT NULL,
is_default tinyint(1) DEFAULT NULL,
attivo tinyint(1) DEFAULT NULL,

PRIMARY KEY (id_immagine)
);

CREATE TABLE IF NOT EXISTS richieste_file(
id_file int(11) NOT NULL AUTO_INCREMENT,
id_richiesta int(11) DEFAULT NULL,
filename varchar(45) DEFAULT NULL,
attivo tinyint(1) DEFAULT NULL,

PRIMARY KEY (id_file)
);

CREATE TABLE IF NOT EXISTS richieste_immagini(
id_immagine int(11) NOT NULL AUTO_INCREMENT,
id_richiesta int(11) DEFAULT NULL,
filename varchar(45) DEFAULT NULL,
is_default tinyint(1) DEFAULT NULL,
attivo tinyint(1) DEFAULT NULL,

PRIMARY KEY (id_immagine)
);


/* ************************ Foreign Key ************************ */

/* tabella utenti */
ALTER TABLE utenti
ADD FOREIGN KEY (tipo_utente) REFERENCES tipo_utenti(tipo_utente);

/* tabella richieste */
ALTER TABLE richieste
ADD FOREIGN KEY(id_utente) REFERENCES utenti(id_utente),
ADD FOREIGN KEY(nome_materia) REFERENCES materie(nome);

/* tabella documenti */
ALTER TABLE documenti
ADD FOREIGN KEY (nome_materia) REFERENCES materie(nome);

/* tabella recensioni*/
ALTER TABLE recensioni
ADD FOREIGN KEY(username) REFERENCES utenti(username),
ADD FOREIGN KEY(codice) REFERENCES documenti(codice);

/* tabella immagini documenti*/
ALTER TABLE documenti_immagini
ADD FOREIGN KEY(codice) REFERENCES documenti(codice);

/* tabella file richieste*/
ALTER TABLE richieste_file
ADD FOREIGN KEY(id_richiesta) REFERENCES richieste(id_richiesta);

/* tabella richieste_immagini*/
ALTER TABLE richieste_immagini
ADD FOREIGN KEY(id_richiesta) REFERENCES richieste(id_richiesta);
/* tabella ordini*/

ALTER TABLE ordini
ADD FOREIGN KEY(id_utente) REFERENCES utenti(id_utente),
ADD FOREIGN KEY(id_vettore) REFERENCES ordini_vettori(id_vettore),
ADD FOREIGN KEY(id_metodo) REFERENCES ordini_metodi_pagamento(id_metodo),
ADD FOREIGN KEY(id_indirizzo) REFERENCES indirizzi(id_indirizzo);

/* tabella ordini_documenti*/
ALTER TABLE ordini_documenti
ADD FOREIGN KEY(serial_id) REFERENCES ordini(serial_id),
ADD FOREIGN KEY(codice) REFERENCES documenti(codice),
ADD FOREIGN KEY(nome_materia) REFERENCES materie(nome);

/*tabella Indirizzi*/
ALTER TABLE indirizzi
ADD FOREIGN KEY(id_utente) REFERENCES utenti(id_utente);



/* ************************ Popolamento ************************ */

/*TipoUtente*/
INSERT INTO tipo_utenti(tipo_utente,nome)
VALUES(2,'Studente');
INSERT INTO tipo_utenti(tipo_utente,nome)
VALUES(1,'Gestore');

/*Utente*/
INSERT INTO utenti(id_utente,username,nome,cognome,email,paswd,tipo_utente,attivo) 
VALUES(1,'maiomat97','Matteo','Maiorano','maiomat@hotmail.it','7725f2f57f982715b89902cef418dfc3',1,1); /*passw: maiomat*/
INSERT INTO utenti(id_utente,username,nome,cognome,email,paswd,tipo_utente,attivo) 
VALUES(2,'lacortiglia01', 'Marco', 'La cortiglia','lacortiglia@gmail.com', 'f2047aa3df533738c5f3e7e70df93c79',2,1); /*passw: marco1*/
INSERT INTO utenti(id_utente,username,nome,cognome,email,paswd,tipo_utente,attivo) 
VALUES(3,'franpa96','Francesco', 'Parisi','f.parisi896@libero.it', '0B5DD095AC09918EC1861BDC40DE69AA',1,1); /*passw: gestore96*/
INSERT INTO utenti(id_utente,username,nome,cognome,email,paswd,tipo_utente,attivo) 
VALUES(4,'mick99','Michele', 'Rullo','m.rullo@gmail.com', '062078502e2a4c64764ba80f3cc3d279',2,1); /*passw: mrullo*/

/*Materie*/
INSERT INTO materie(nome,codice,flag)
VALUES('Arte',1,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Analisi I',2,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Biochimica',3,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Basi di Dati',4,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Cinematica',5,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Chimica',6,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Comunicazione Politica',7,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Dietetica',8,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Economia',9,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Farmacia',10,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Genetica',11,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Ingegneria del Software',12,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Letteratura Inglese',13,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Meccanica',14,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Patologia',15,1);
INSERT INTO materie(nome,codice,flag)
VALUES('Telecomunicazioni',16,1);

/*Documenti:Appunti*/
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1001,'Appunti di storia della fotografia',8,'Universita degli studi di Salerno','Arte','Storia della fotografia-breve ma utile sintesi da "fotografia e pittura nel novecento." (marra).',1.99,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1002,'La Camera Chiara - Roland Barthes',14,'Università degli studi di Trieste','Arte','Arte - La Camera chiara - Roland Barthes. ',2.49,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1003,'Tabella degli integrali impropri notevoli',11,'Universita degli studi di Bologna','Analisi I','Tabella degli integrali impropri notevoli. ',2.00,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1004,'Appunti di analisi 1 - Le Derivate',7,'Universita degli studi di Bologna','Analisi I','Appunti di Analisi 1 del prof. Fonda. ',1.50,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1005,'Appunti Di Biochimica (Scienze Motorie)',16,'Università degli studi di Trieste','Biochimica','Appunti biochimica. Capitolo I: Introduzione al metabolismo pag. 1 1. Cenni elementari di termodinamica chimica 2. Catalizzatori ed enzimi... ',3.99,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1006,'Nuovi approcci alla comunicazione politica ',29,'Universita degli studi di Pisa','Comunicazione Politica','Riassunto dettagliato del libro.',2.99,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1007,'Comunicazione, poteri e cittadini rolando',21,'Universita degli studi di Salerno','Comunicazione Politica','Non sono tutti i capitoli ma comunque i più importanti del libro.',4.45,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1008,'Biochimica, 1° Anno Infermieristica',14,'Università degli studi di Milano','Biochimica',' Anno 2014-15, corso di Laurea in infermieristica.',3.99,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1009,'Basi di dati, Appunti di Basi Di Dati',5,'Università degli studi di Napoli Federico II','Basi di Dati','Appunti del corso di laurea di basi di dati.',2,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1010,'Uso del linguaggio procedurale per il database',7,'Università Politecnica delle Marche','Basi di Dati','PL SQL. Prof. Basili, corso di Basi di dati. Università Politecnica delle Marche.',2.50,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1011,'Pastorale americana, Appunti di Cinematica',15,'Libera università di lingue e comunicazione (IULM)','Cinematica','Riassunto',3.25,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1012,'Interpretazione dei film',7,'Università degli Studi di Verona','Cinematica','Riassunto libro di Bertetto con analisi di 10 film.',2.50,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1013,'Prodotti Cosmetici, Appunti di Chimica',50,'Università degli Studi della Basilicata','Chimica','Appunti di chimica dei prodotti cosmetici..esame a scelta del corso di laurea in farmacia.',11.55,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1014,'Nomenclatura Chimica Inorganica',45,'Università degli Studi di Milano','Chimica',' Tabella riassuntiva della nomenclatura di chimica inorganica.',2.99,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1015,'Dispense di analisi 1',11,'Universita degli studi di Bologna','Analisi I','Dispense di Analisi 1 del prof. Fonda. ',2.00,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1016,'Dispense Di Biochimica (Scienze Motorie)',16,'Università degli studi di Trieste','Biochimica','Dispense biochimica. Capitolo I: Introduzione al metabolismo pag. 1 1. Cenni elementari di termodinamica chimica 2. Catalizzatori ed enzimi... ',3.99,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1017,'Riassunto Ormoni',16,'Università degli studi di Pisa','Biochimica','Riassunto biochimica per facoltà di scienze motorie. ',1.50,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1018,'Comunicazione, poteri e cittadini rolando',21,'Universita degli studi di Salerno','Comunicazione Politica','Non sono tutti i capitoli ma comunque i più importanti del libro.',3.45,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1019,'Modifiche epigenetiche, Appunti di Dietetica.',3,'Università degli Studi di Napoli Parthenope','Dietetica','Modifiche epigenetiche in gravidanza,in maternite modifich',1.49,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1020,'Dieta dukan, Appunti di Dietetica',3,'Alma Mater Studiorum – Università di Bologna','Dietetica','Appunti alimentazione.',3.49,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1021,'Lipidi',6,'Università degli Studi G. d Annunzio-Pescara','Dietetica','Appunti scienze dietetiche.',2.49,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1022,'Comportamento Organizzativo',26,'Libera università di lingue e comunicazione (IULM)','Economia','Riassunti lezioni del corso: libro consigliato di Comportamento Organizzativo H.Tosi M.Pilati.',3.29,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1023,'Economia aziendale lo sviluppo del turismo crocieristico',7,'Economia,Istituto Tecnico Economico e del Turismo','Economia','Lo sviluppo del turismo crocieristico.',1.29,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1024,'Appunti Preparazioni parenterali',7,'Università degli Studi di Napoli Federico II','Farmacia','Tecnica e legislazione farmaceutica I - Tecnica.',1.29,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1025,'Macerazione, soluzioni estrattive, droghe vegetali',15,'Università degli Studi di Napoli Federico II','Farmacia','Tecnica e legislazione farmaceutica.',2.99,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1026,'Farmacognosia Applicata',7,'Università degli Studi di Roma La Sapienza','Farmacia','Introduzione, raccolta, variabilità, controllo qualità, tecniche estrattive e di preparazione e conservazione delle droghe vegetali e saggi di analisi.',3.55,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1027,'Appunti di genetica - Parte 1',14,'Università Vita-Salute San Raffaele','Genetica','In questa parte degli appunti di genetica si parla di: Fecondazione artificiale dei piselli di Mendel, incroci, test cross, locus e cromosoma.',2.55,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1028,'Genetica anomalie cromosomiche',59,'Università degli Studi di Sassari','Genetica','Appunti sull esame di genetica sulle anomalie cromosomiche.',4.95,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1029,'Brick Lane - Summary chapter by chapter',29,'Università degli Studi di Napoli L Orientale','Letteratura Inglese','Riassunto del romanzo di Monica Ali.',1.95,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1030,'Letteratura Inglese Sanders vol.2 capitolo 1,2,3',46,'Università della Calabria','Letteratura Inglese','Il Romanticismo, L età vittoriana, L età tardovittoriana ed edoardian.',2.15,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1031,'Riassunto letteratura spagnola, dal cid ai re cattolici',19,'Università degli Studi di Napoli L Orientale','Letteratura Inglese','Riassunto della prof notaro, unior.',3.35,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1032,'Appunti di: Meccanica Applicata alle Macchine',109,'Politecnico di Torino','Meccanica','Appunti di: Meccanica Applicata alle Macchine - per Studenti di Ingegneria Meccanica - Nuovo OrdinamentoDipartimento di MeccanicaUniversità della Calabria - Proff. Danieli - Mundo.',3.35,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1033,'Meccanica delle strutture',17,'Università Iuav di Venezia','Meccanica','Primo anno, prima lezione facile comprensione e capibili da tutti.',2.45,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1034,'Patologia - Appunti, Appunti di Patologia',9,'Università Iuav di Venezia','Patologia','Primo anno, prima lezione facile comprensione e capibili da tutti.',4.05,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1035,'Fisiopatologia della Tiroide',75,'Università di Urbino Carlo Bo','Patologia','Fisioanatomia della tiroide-ormoni tiroidei-patologie della tiroide.',6.25,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1036,'Appunti Ingegneria del software',27,'Università di Salerno','Ingegneria del Software','Gli appunti riguardano parte del corso di ingegneria del software',2.25,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1037,'Ricerca unione europea con trattati di Roma e Brexit',27,'Università di Salerno','Telecomunicazioni','Gli appunti riguardano parte del corso di ingegneria del software',2.25,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1038,'La pubblicità nel marketing',2,'Istituto Tecnico Tecnologico','Telecomunicazioni','Riassunto capitolo 5 Principi di marketing',3.65,'Appunti',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1039,'Il Museo Oggi',11,'Universita degli studi di Palermo','Arte','Riassunti dei capitoli del libro "Il museo Oggi.',2.45,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1040,'L’opera d’arte benjamin',5,'Universita degli studi di Bologna','Arte','L’opera d’arte benjamin.pdf.',1.85,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1041,'Progettazione concettuale',23,'Universita degli studi di Torino','Basi di Dati','Dispense dal libro Basi di dati di Atzeni, Ceri, Paraboschi, Torlone - McGraw-Hill',2.25,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1042,'Fondamenti di Chimica',45,'Universita degli studi di Trieste','Chimica','Basi generali di chimica inorganica, appunti utili per la trienale in chimica',6.80,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1043,'Cinema e guerra',9,'Alma Mater Studiorum – Università di Bologna','Cinematica','Cinema e guerra - montaggio esmae dams bologna',1.50,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1044,'Valutazione Nutrizionale',20,'Universita degli studi di Padova','Dietetica','Corso di VALUTAZIONE NUTRIZIONALE UNIPD',2.50,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1045,'Composizione Chimica',12,'Universita degli studi di Padova','Dietetica','VALUTAZIONE NUTRIZIONALE',1.70,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1046,'Microeconomia - Teoria del consumatore',57,'Universita degli studi di Firenze','Economia','Dispensa di microeconomia',7.90,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1047,'Tabella soluzioni idroalcoliche',11,'Universita degli studi di Salerno','Farmacia','Tabella delle soluzioni idroalcoliche trattate in laboratorio di tecniche farmaceutiche per la risoluzione di esercizi a titolo alcolimetrico.',4.90,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1048,'Guida riconoscimento cromosomi',9,'Universita degli studi di Napoli Federico II','Genetica','Corso perfezionamento citogenetica unina',2.35,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1049,'Homework relativi alla progettazione del software',6,'Università telematica internazionale UniNettuno','Ingegneria del Software','Domande risposte esame di progettazione del software',1.95,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1050,'Progettazione software',20,'Università degli Studi di Roma La Sapienza','Ingegneria del Software','UML - Unified Modeling Language',3.45,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1051,'Macbeth riassunto in atti',20,'Università degli Studi di Napoli Orientale','Letteratura Inglese','Riassunto dettagliato e breve di Macbeth in atti',2.55,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1052,'Turbocompressore componenti e storia',15,'Università degli Studi di Torino','Meccanica','Funzionalità del Turbocompressore',2.99,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1053,'Coagulazione del sangue',12,'Università degli Studi di Urbino Carlo Bo','Patologia','Slides sulla coagulazione',2.49,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1054,'Termoregolazione e fisiopatologia',132,'Università degli Studi di Roma La Sapienza','Patologia','Termoregolazione. SISTEMA DI TERMOREGOLAZIONE, IPERTERMIE, febbre...',8.99,'Dispense',1);
INSERT INTO documenti(codice,titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag)
VALUES(1055,'Strategie di comunicazione',18,'Università degli Studi di Verona','Telecomunicazioni','Dispense di tecnica della comunicazione pubblicitari',8.99,'Dispense',1);




/*Appunti-Immagini*/
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(1,1001,'appunti-ilmondocontemporaneo.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(2,1002,'La_camera_chiara.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(3,1003,'Tabella_degli_Integrali.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(4,1004,'appunti_Analisi_1.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(5,1005,'Appunti_Di_Biochimica_Scienze_Motorie.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(6,1006,'appunti_di_ comunicazione_politica.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(7,1007,'comunicazione_poteri_e_cittadini.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(8,1008,'appunti_Biochimica.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(9,1009,'BASI_DI_DATI.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(10,1010,'PL_SQL.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(11,1011,'PASTORALE_AMERICANA.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(12,1012,'interpretazione dei film.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(13,1013,'Prodotti_Cosmetici.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(14,1014,'Nomenclatura_Chimica_Inorganica.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(15,1015,'dispense_Analisi_1.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(16,1016,'Appunti_Di_Biochimica_Scienze_Motorie.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(17,1017,'14_ORMONI.doc.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(18,1018,'Comunicazione_poteri_e_cittadini.docx.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(19,1019,'dietetica.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(20,1020,'dieta_dukan.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(21,1021,'Lipidi.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(22,1022,'Comportamento_Organizzativo.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(23,1023,'Economia_aziendale_lo_sviluppo_del_turismo_crocieristico.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(24,1024,'15._Preparazioni_parenterali.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(25,1025,'44d3ee0_1._Macerazione_soluzioni_estrattive_droghe.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(26,1026,'pl9k6k-FARMACOGNOSIA APPLICATA.docx.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(27,1027,'Genetica_-_Appunti_-_Parte_1_.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(28,1028,'pls3q2-132299701.docx.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(29,1029,'Brick_Lane__Summary_chapter_by_chapter_docx.docx.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(30,1030,'Letteratura_Inglese_Sanders_vol_2_.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(31,1031,'riass_notaro_pt_1.docx.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(32,1032,'Appunti_di__Meccanica_Applicata_alle_Macchine_-_Prof__Daniel.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(33,1033,'pjfbug-meccanica 4.12.18.pdf.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(34,1034,'Patologia_-_Appunti.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(35,1035,'fisiopatologia_della_tiroide.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(36,1036,'p4zdyl-Riassunto-Ing-Software.pdf.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(37,1037,'UNIONE EUROPEA.docx.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(38,1038,'p8gz28-pubblicita.doc.jpg',1,1);

/*Dispense-Immagini*/

INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(39,1039,'IL_MUSEO_OGGI.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(40,1040,'L_opera_d_arte_benjamin.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(41,1041,'Basi_di_dati_-_Dispense_dal_libro_Atzeni.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(42,1042,'p6bdrf-Fondamenti_di_chimica.pdf.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(43,1043,'pgdxhh-20150529cinema_e_guerra.pdf.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(44,1044,'2_Lezione02_anatomia_e_fisiologia_digestiva.pdf.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(45,1045,'4_Lezione04_composizione_chimica.pdf.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(46,1046,'microeconomia_dispensa.pdf.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(47,1047,'tabella_soluzioni_idroalcoliche.pdf.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(48,1048,'phxbnm-Guida riconoscimento Cromosomi.pdf.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(49,1049,'p4jqmt-hw3_RC.pdf.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(50,1050,'p2lfut-progettazione software.pdf.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(51,1051,'Macbeth_riassunto_in_atti.odt.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(52,1052,'tesina-completa.docx.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(53,1053,'07_coagulazione_pdf.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(54,1054,'Documento_Silvia.doc.jpg',1,1);
INSERT INTO documenti_immagini(id_immagine,codice,filename,is_default,attivo)
VALUES(55,1055,'lastrategiadicomunicazione.pdf.jpg',1,1);



/*Recensioni-Documenti*/
INSERT INTO recensioni(id_recensione,descrizione,username,codice,flag)
VALUES(1,'Ottimo Appunto.','lacortiglia01',1002,1);
INSERT INTO recensioni(id_recensione,descrizione,username,codice,flag)
VALUES(2,'Consigliato','mick99',1002,1);
INSERT INTO recensioni(id_recensione,descrizione,username,codice,flag)
VALUES(3,'Perfetto','lacortiglia01',1004,1);




/*Richieste*/
INSERT INTO richieste(id_richiesta,titolo,pagine,nome_materia,universita,descrizione,tipo,data_richiesta,id_utente,attivo)
VALUES(100,'Prova',100,'Analisi I','Universita degli studi di Salerno','prova prova prova','Appunti','2018-12-13',2,1);
INSERT INTO richieste(id_richiesta,titolo,pagine,nome_materia,universita,descrizione,tipo,data_richiesta,id_utente,attivo)
VALUES(101,'Comunicazione, cultura e società',13,'Telecomunicazioni','Università Cattolica del Sacro Cuore - Milano','Appunti per l esame di Teoria della comunicazione e dei media - Fausto Colomb','Appunti','2019-01-19',4,1);
INSERT INTO richieste(id_richiesta,titolo,pagine,nome_materia,universita,descrizione,tipo,data_richiesta,id_utente,attivo)
VALUES(102,'Il Modello Relazionale',43,'Basi di Dati','Università degli studi di Salerno','Appunti per l esame di Basi di Dati','Appunti','2019-01-19',4,1);

/*Richieste-File*/
INSERT INTO richieste_file(id_file,id_richiesta,filename,attivo)
VALUES(1,100,'analisi1.pdf',1);
INSERT INTO richieste_file(id_file,id_richiesta,filename,attivo)
VALUES(2,101,'GLI-INTEGRALI-Matematica.pdf',1);
INSERT INTO richieste_file(id_file,id_richiesta,filename,attivo)
VALUES(3,102,'ES1-Modello_Relazionale.pdf',1);

/*ordini_metodi_pagamento*/
INSERT INTO ordini_metodi_pagamento(id_metodo,nome,descrizione,in_contanti,attivo)
VALUES(1,'Contrassegno','Pagamento in Contanti al corriere',1,1);
INSERT INTO ordini_metodi_pagamento(id_metodo,nome,descrizione,in_contanti,attivo)
VALUES(2,'Bonifico Bancario','Pagamento con Bonifico Bancario',0,1);


/*ordini_vettori*/
INSERT INTO ordini_vettori (id_vettore,nome,descrizione,costo,contrassegno,attivo)
VALUES(1,'BRT 48h','Corriere Espresso 48h\r\n',4,1,1);
INSERT INTO ordini_vettori (id_vettore,nome,descrizione,costo,contrassegno,attivo)
VALUES(2,'GLS 24h','Corriere Espresso 24h',7.5,1,1);
INSERT INTO ordini_vettori (id_vettore,nome,descrizione,costo,contrassegno,attivo)
VALUES(3,'SDA','Corriere 48/72h',5,0,1);

/*indirizzi*/

INSERT INTO indirizzi(id_indirizzo,nome,cognome,indirizzo,cap,citta,provincia,telefono,cellulare,id_utente,attivo)
VALUES(3,'Michele','Rullo','Via Giacomo Leoparti 23',80042,'Ottaviano','Napoli',3331123245,0818485639,4,1);

INSERT INTO indirizzi(id_indirizzo,nome,cognome,indirizzo,cap,citta,provincia,telefono,cellulare,id_utente,attivo)
VALUES(2,'Marco','La Cortiglia','Via Garibaldi 61',80042,'Angri','Salerno',312226785,0888445231,2,1);

/*Ordini*/
INSERT INTO ordini(serial_id,id_vettore,id_metodo,id_utente,id_indirizzo,totale_documenti,totale_spedizione,totale_ordine,data_ordine,attivo)
VALUES (100,2,1,2,2,3.98,5.0,8.98,'2018-12-13 11:16:18',1);
INSERT INTO ordini(serial_id,id_vettore,id_metodo,id_utente,id_indirizzo,totale_documenti,totale_spedizione,totale_ordine,data_ordine,attivo)
VALUES (101,3,1,4,3,6.49,7.50,13.49,'2019-01-10 20:01:08',1);



/*Ordini_documenti*/
INSERT INTO ordini_documenti(id_ordine,serial_id,codice,titolo,quantita,prezzo_documenti,prezzo_totale,nome_materia,attivo)
VALUES(1,100,1001,'Appunti di storia della fotografia',2,1.99,3.98,'Arte',1);
INSERT INTO ordini_documenti(id_ordine,serial_id,codice,titolo,quantita,prezzo_documenti,prezzo_totale,nome_materia,attivo)
VALUES(2,101,1002,'La Camera Chiara - Roland Barthes',1,2.49,2.49,'Arte',1);
