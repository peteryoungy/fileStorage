CREATE TABLE IF NOT EXISTS USERS (
    userid INT PRIMARY KEY auto_increment,
    username VARCHAR(20),
    salt VARCHAR,
    password VARCHAR,
    firstname VARCHAR(20),
    lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS FILES (
     fileId INT PRIMARY KEY auto_increment,
     filename VARCHAR,
     contenttype VARCHAR,
     filesize VARCHAR,
     userid INT,
     filedata BLOB,
     foreign key (userid) references USERS(userid)
);