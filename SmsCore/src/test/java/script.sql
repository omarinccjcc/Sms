CREATE TABLE AccessApp(
	id 	BIGINT(20) NOT NULL AUTO_INCREMENT,
	imei	VARCHAR(20) NOT NULL,
	dateIni	TIMESTAMP NULL,
	dateEnd	TIMESTAMP NULL,
	statusAccessApp VARCHAR(20) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE UserSystem
(
	id                   BIGINT NOT NULL AUTO_INCREMENT,
	login                VARCHAR(15) NULL,
	PASSWORD             VARCHAR(15) NULL,
	dateIni              DATETIME NULL,
	dateEnd              DATETIME NULL,
	firstName            VARCHAR(20) NULL,
	lastNameF            VARCHAR(20) NULL,
	lastNameM            VARBINARY(20) NULL,
	statusUser           VARCHAR(20) NULL,
	PRIMARY KEY (id)
);



CREATE TABLE role
(
	id                   BIGINT NOT NULL,
	roleName             VARCHAR(30) NULL,
	description          VARCHAR(100) NULL,
	url                  VARCHAR(20) NULL,
	statusRole           VARCHAR(20) NULL,
	PRIMARY KEY (id)
);



CREATE TABLE UserSystemRole
(
	userId               BIGINT NOT NULL,
	roleId               BIGINT NOT NULL,
	PRIMARY KEY (userId,roleId),
	FOREIGN KEY R_2 (userId) REFERENCES UserSystem (id),
	FOREIGN KEY R_3 (roleId) REFERENCES role (id)
);