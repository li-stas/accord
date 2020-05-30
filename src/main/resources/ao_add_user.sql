
-- ao_USER
INSERT INTO ao_USER (USERNAME, PASSWORD, ENABLED,login,KTA) VALUES ('admin@gmail.com','12345',1,'login',0);
INSERT INTO ao_USER (USERNAME, PASSWORD, ENABLED,login,KTA) VALUES ('roleuser@outlook.com','12345',1,'login',462);
INSERT INTO ao_USER (USERNAME, PASSWORD, ENABLED,login,KTA) VALUES ('javastudy@outlook.com','12345',1,'login',462);
INSERT INTO ao_USER (USERNAME, PASSWORD, ENABLED,login,KTA) VALUES ('superuser@outlook.com','12345',1,'login',31);

INSERT INTO ao_USER (IDUSER,USERname,login,password,KTA, ENABLED)
VALUES (51,'login51@accord.com','login51','12345',51,1);

INSERT INTO ao_USER (IDUSER,USERname,login,password,KTA, ENABLED)
VALUES (31,'login31@accord.com','login51','12345',31,1);

INSERT INTO ao_USER (IDUSER,USERname,login,password,KTA, ENABLED)
VALUES (364,'login364@accord.com','login364','12345',364,1);

INSERT INTO ao_USER (IDUSER,USERname,login,password,KTA, ENABLED)
VALUES (461,'login461@accord.com','login461','12345',461,1);

INSERT INTO ao_USER (IDUSER,USERname,login,password,KTA, ENABLED)
VALUES (462,'login462@accord.com','login462','12345',462,1);


INSERT INTO ao_AUTHORITIES (USERname, AUTHORITY) VALUES ('admin@gmail.com','ROLE_ADMIN');
INSERT INTO ao_AUTHORITIES (USERname, AUTHORITY) VALUES ('roleuser@outlook.com','ROLE_USER');
INSERT INTO ao_AUTHORITIES (USERname, AUTHORITY) VALUES ('superuser@outlook.com','ROLE_SUPER_USER');
INSERT INTO ao_AUTHORITIES (USERname, AUTHORITY) VALUES ('login51@accord.com','ROLE_SUPER_USER');
INSERT INTO ao_AUTHORITIES (USERname, AUTHORITY) VALUES ('login31@accord.com','ROLE_SUPER_USER');
INSERT INTO ao_AUTHORITIES (USERname, AUTHORITY) VALUES ('login364@accord.com','ROLE_USER');
INSERT INTO ao_AUTHORITIES (USERname, AUTHORITY) VALUES ('login461@accord.com','ROLE_SUPER_USER');
INSERT INTO ao_AUTHORITIES (USERname, AUTHORITY) VALUES ('login462@accord.com','ROLE_USER');