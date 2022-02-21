package com.peteryu.filestorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileStorageApplication.class, args);
    }

}

// TODO:
//  1. files can be stored in another place like s3, only store s3 in the database
//  2. inversion of control is not completed deployed in this program


// why:
//  1.


