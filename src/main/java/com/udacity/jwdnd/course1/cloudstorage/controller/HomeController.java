package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;

    public  HomeController (FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }
    @GetMapping()
    public String getHomePage (Model model, Authentication authentication, NoteForm noteForm) {

        List<Note> notes = noteService.getAllNotes(authentication);

        System.out.println("Note size: "+notes.size());
        System.out.println("Note display from home controller");
        for (Note n : notes) {
            System.out.println(n);

        }

        model.addAttribute("notes", notes);
        return "home";
    }


}
