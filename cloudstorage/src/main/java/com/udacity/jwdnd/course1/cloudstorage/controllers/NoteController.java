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

//        String noteUploadError = null;
//        String noteUploadSuccess = null;

        if(note.getNoteId() == null) {

            try {
                this.noteService.addNote(note, user);
//                noteUploadSuccess = "true";
//                model.addAttribute("noteUploadSuccess", noteUploadSuccess);
                return "redirect:/result?success";
            }
            catch (Exception e){
//                noteUploadError = e.toString();
//                model.addAttribute("noteError", noteUploadError);
                return "redirect:/result?error";
            }
        }
        else {
            try {
                this.noteService.updateNote(note);
              //  model.addAttribute("noteUploadSuccess", "true");
                return "redirect:/result?success";
            }
            catch (Exception e){
//                noteUploadError = e.toString();
//                model.addAttribute("noteError", noteUploadError);
                return "redirect:/result?error";
            }}
    }

    @RequestMapping("/deleteNote/{noteId}")
    public String deleteNote(@PathVariable int noteId, Note note , User user, Model model)throws IOException{
//        String noteDeleteError = null;
//        String noteDeleteSuccess = null;
        try {
            this.noteService.deleteNote(noteId);
//            noteDeleteSuccess = "true";
//            model.addAttribute("noteDeleteSuccess", "true");
            return "redirect:/result?success";

        }
        catch (Exception e){
         //   noteDeleteError = e.toString();
           //  model.addAttribute("noteDeleteError", noteDeleteError);
            return "redirect:/result?error";
        }
    }
}
