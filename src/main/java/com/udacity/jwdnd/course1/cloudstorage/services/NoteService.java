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
    private NoteMapper noteMapper;
    private UserMapper userMapper;

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
        System.out.println("title: " + title);
        System.out.println(("description: " + description));
        System.out.println(("userID: " + userID));

        System.out.println("note to be added: "+ note);

        noteMapper.addNote(note);
    }

    public List<Note> getAllNotes (Authentication authentication) {
        int id = userMapper.getID(authentication.getName());
        System.out.println("User id: " + id);
        List<Note> list = noteMapper.getAllNotes(id);

        return list;
    }
}
