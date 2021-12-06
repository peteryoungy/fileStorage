CREATE TABLE IF NOT EXISTS USERS (
    userid INT PRIMARY KEY auto_increment,
    username VARCHAR(256),
    salt VARCHAR(256),
    password VARCHAR(256),
    firstname VARCHAR(256),
    lastname VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS users (
     userid INT PRIMARY KEY auto_increment,
     username VARCHAR(256),
     salt VARCHAR(256),
     password VARCHAR(256),
     firstname VARCHAR(256),
     lastname VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS files (
     fileId INT PRIMARY KEY auto_increment,
     filename VARCHAR(256),
     contenttype VARCHAR(256),
     filesize VARCHAR(256),
     userid INT,
     filedata BLOB,
     foreign key (userid) references users(userid)
);