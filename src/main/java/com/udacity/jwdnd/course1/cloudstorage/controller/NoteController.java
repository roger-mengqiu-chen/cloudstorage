package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class NoteController {
    private NoteService noteService;

    public NoteController (NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping("/add")
    public String addNote(NoteForm noteForm, Model model, Authentication authentication) {

        String title = noteForm.getTitle();
        String description = noteForm.getDescription();
        String username = authentication.getName();
        noteService.addNote(title, description, username);

        return "/result";
    }
}
