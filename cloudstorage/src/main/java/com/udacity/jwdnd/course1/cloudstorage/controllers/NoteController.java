package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
public class NoteController {
    private NoteService noteService;

    private UserService userService;

    public NoteController(NoteService noteService, UserService userService){
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/addNote")
    public String addNote(Note note , Authentication auth) throws IOException {
        User user = this.userService.getUser(auth.getName()) ;
        if(note.getNoteId() == null) {

            try {
                this.noteService.addNote(note, user);
                return "redirect:/result?success";
            }
            catch (Exception e){
                return "redirect:/result?error";
            }
        }
        else {
            try {
                this.noteService.updateNote(note);
                return "redirect:/result?success";
            }
            catch (Exception e){
                return "redirect:/result?error";
            }}
    }

    @RequestMapping("/deleteNote/{noteId}")
    public String deleteNote(@PathVariable int noteId)throws IOException{
        try {
            this.noteService.deleteNote(noteId);
            return "redirect:/result?success";

        }
        catch (Exception e){
            return "redirect:/result?error";
        }
    }
}
