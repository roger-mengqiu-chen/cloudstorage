package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
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
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public  HomeController (FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }
    @GetMapping()
    public String getHomePage (Model model
                                , Authentication authentication
                                , NoteForm noteForm
                                , CredentialForm credentialForm
                                , FileForm fileForm) {

        List<Note> notes = noteService.getAllNotes(authentication);
        List<Credential> credentials = credentialService.getAllCredentialsOfUser(authentication);
        List<File> files = fileService.getAllFiles(authentication);
        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);
        model.addAttribute("files", files);
        return "home";
    }


}
