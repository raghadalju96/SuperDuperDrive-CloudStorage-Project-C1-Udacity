package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
//@RequestMapping("/note")

public class NoteController {
    private NoteService noteService;

    private UserService userService;

    private NoteMapper noteMapper;

    public NoteController(NoteService noteService, UserService userService){
        this.noteService = noteService;
        this.userService = userService;
    }



//         @GetMapping()
//        public Note[] getAllNotes(){
//          return this.noteMapper.getNotes();
//        }


    @PostMapping("/addNote")
    public String addNote(@RequestParam("noteId") Integer noteId, Note note , Authentication auth, Model model){
        User user = this.userService.getUser(auth.getName());

        if(noteId == null) {
            this.noteService.addNote(note, user);
        }

        else {
            this.noteService.updateNote(note);
        }
        this.noteService.getNotes(user);


        model.addAttribute("notes",  this.noteService.getNotes(user));
        System.out.println(model.getAttribute("notes"));

        System.out.println("xxx");
        System.out.println(model.getAttribute("noteDescription"));

        return "result";
    }

    @GetMapping("/deleteNote/{noteId}")
    public String deleteNote(@PathVariable int noteId, Note note , User user, Model model){
        System.out.println("delete");

        this.noteService.deleteNote(noteId);


        return "result";
    }
}
