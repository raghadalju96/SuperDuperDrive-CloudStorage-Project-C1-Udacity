package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {


    private FileMapper fileMapper;


    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }


    public int addFile(MultipartFile file, int userId) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File fileModel = new File();
        fileModel.setFileName(fileName);
        fileModel.setContentType(file.getContentType());
        fileModel.setFileSize(file.getSize());
        fileModel.setUserId(userId);
        fileModel.setFileData(file.getBytes());

        return this.fileMapper.insertFile(fileModel);
    }


    public boolean isFileNameAvailable(String fileName){
        return this.fileMapper.getFile(fileName) == null;
    }


    public List<File> getAllFiles(int userId) {
        return this.fileMapper.getFiles(userId);
    }

    public void deleteFile(int fileId){
        this.fileMapper.deleteFile(fileId);
    }


    public File getFileById(Integer fileId) {
        return fileMapper.findById(fileId);
    }

    public ResponseEntity downloadFile(File file) {
        String contentType = file.getContentType();
        String fileName = file.getFileName();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(file.getFileData());
    }


}
