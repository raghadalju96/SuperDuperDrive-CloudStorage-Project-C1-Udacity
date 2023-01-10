package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;

    private UserService userService;

    public HomeController(NoteService noteService, UserService userService){
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping()
    public String home(Authentication authentication, Model model){

       String username = authentication.getName();
       System.out.println(username);
       User user = this.userService.getUser(username);
        System.out.println(noteService.getNotes(user));
       model.addAttribute("notes", noteService.getNotes(user));

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