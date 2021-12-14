package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private final UserMapper userMapper;

    public NoteService (NoteMapper noteMapper, UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
    }

    @PostConstruct
    public void postConstruct () {
        System.out.println("Creating NoteService bean");
    }

    public void addNote (String title, String description, String username) {

        int userID = userMapper.getID(username);

        Note note = new Note();
        note.setTitle(title);
        note.setDescription(description);
        note.setUserid(userID);

        noteMapper.addNote(note);
    }

    public List<Note> getAllNotes (Authentication authentication) {
        int id = userMapper.getID(authentication.getName());
        return noteMapper.getAllNotes(id);
    }

    public Note getNoteById(int id){
        return noteMapper.getNoteById(id);
    }

    public int update(int id, String title, String desc) {
        return noteMapper.updateNote(id, title, desc);
    }
}
