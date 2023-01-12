package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    private FileService fileService;

    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @RequestMapping("/addFile")
    public String addFile(MultipartFile fileUpload, Model model, Authentication authentication) throws IOException {

        String username = authentication.getName();
        User user = this.userService.getUser(username);

        this.fileService.addFile(fileUpload, user.getUserId());
        this.fileService.getAllFiles(user.getUserId());
        model.addAttribute("files", this.fileService.getAllFiles(user.getUserId()));
        System.out.println(model.getAttribute("files"));

        return "result";
    }


    @RequestMapping("/file/view/{fileId}")
    public ResponseEntity downloadFile(@PathVariable("fileId") Integer fileId,
                                       Authentication auth,
                                       Model model) throws IOException {
        File file = this.fileService.getFileById(fileId);
        return this.fileService.downloadFile(file);

    }

    @RequestMapping("/deleteFile/{fileId}")
    public String deleteFile(@PathVariable int fileId){
        this.fileService.deleteFile(fileId);

        return "result";
    }
}
