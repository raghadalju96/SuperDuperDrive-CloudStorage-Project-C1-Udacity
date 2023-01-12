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

import java.io.IOException;


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

    @PostMapping("/addNote")
    public String addNote(Note note , Authentication auth, Model model) throws IOException {
        User user = this.userService.getUser(auth.getName()) ;

        String noteUploadError = null;
        String noteUploadSuccess = null;

        if(note.getNoteId() == null) {

            try {
                this.noteService.addNote(note, user);
                noteUploadSuccess = "true";
                model.addAttribute("noteUploadSuccess", noteUploadSuccess);
            }
            catch (Exception e){
                noteUploadError = e.toString();
                model.addAttribute("noteError", noteUploadError);
            }
        }
        else {
            try {
                this.noteService.updateNote(note);
                model.addAttribute("noteUploadSuccess", "true");
            }
            catch (Exception e){
                noteUploadError = e.toString();
                model.addAttribute("noteError", noteUploadError);
            }}
        this.noteService.getNotes(user);
        model.addAttribute("notes",  this.noteService.getNotes(user));
        return "result";
    }

    @GetMapping("/deleteNote/{noteId}")
    public String deleteNote(@PathVariable int noteId, Note note , User user, Model model)throws IOException{
        String noteDeleteError = null;
        String noteDeleteSuccess = null;
        try {
            this.noteService.deleteNote(noteId);
            noteDeleteSuccess = "true";
            model.addAttribute("noteDeleteSuccess", "true");
        }
        catch (Exception e){
            noteDeleteError = e.toString();
            model.addAttribute("noteDeleteError", noteDeleteError);

        }
        return "result";
    }
}
