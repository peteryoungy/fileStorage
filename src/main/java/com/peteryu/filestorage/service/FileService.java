package com.peteryu.filestorage.service;

import com.peteryu.filestorage.mapper.FileMapper;
import com.peteryu.filestorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public boolean isDuplicate(String fileName, int userId){

        return fileMapper.getFile(userId, fileName) != null;
    }

    public int addFile(File file){

        return fileMapper.insert(file);
    }

    public List<File> getAllFilesForUser(int userId){

        return fileMapper.getFiles(userId);
    }

    public int deleteFile(int userId, String fileName){
        return fileMapper.delete(userId, fileName);
    }

    public File getFile(int userId, String fileName){

        return fileMapper.getFile(userId, fileName);
    }
}
