package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;

    private UserService userService;

    private CredentialService credentialService;

    private FileService fileService;

    public HomeController(NoteService noteService, UserService userService, CredentialService credentialService, FileService fileService){
        this.noteService = noteService;
        this.userService = userService;
        this.credentialService =credentialService;
        this.fileService = fileService;
    }

    @GetMapping()
    public String home(Authentication authentication, Model model){

       String username = authentication.getName();
       System.out.println(username);
       User user = this.userService.getUser(username);
       model.addAttribute("files", this.fileService.getAllFiles(user.getUserId()));
       model.addAttribute("notes", this.noteService.getNotes(user));
       model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));

        return "home";
    }

//    @PostMapping("/addNote")
//    public String addNote(Note note , User user, Model model){
//        System.out.println("raghad meeeej");
//        this.noteService.addNote(note , user);
//
//        model.addAttribute("noteTitle", note.getNoteTitle());
//        model.addAttribute("noteDescription", note.getNoteDescription());
//        System.out.println("xxx");
//        System.out.println(model.getAttribute("noteDescription"));
//
//
//        return "result";
//    }
}