package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int addNote(Note note, User user){
        note.setNoteTitle(note.getNoteTitle());
        note.setNoteDescription(note.getNoteDescription());
        note.setUserId(user.getUserId());
      return  this.noteMapper.insertNote(note);

    }

    public List<Note> getNotes(User user){
        return this.noteMapper.getNotes(user.getUserId());
    }

    public void deleteNote(int noteId){
        this.noteMapper.deleteNote(noteId);
    }

    public void updateNote(Note note){
        note.setNoteTitle(note.getNoteTitle());
        note.setNoteDescription(note.getNoteDescription());
        this.noteMapper.updateNote(note);
    }


}
