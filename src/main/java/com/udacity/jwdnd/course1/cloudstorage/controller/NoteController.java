package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class NoteController {
    private final NoteService noteService;

    public NoteController (NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping("/note/add")
    public String addNote(NoteForm noteForm, Model model, Authentication authentication) {

        String title = noteForm.getTitle();
        String description = noteForm.getDescription();
        String noteId = noteForm.getNoteId();
        String username = authentication.getName();

        if (noteService.isDuplicate(title, authentication)) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", "Note already existed");
            return "result";
        }
        else {
            try {
                if (noteId == null || noteId.equals("")) {

                    noteService.addNote(title, description, username);
                    model.addAttribute("result", "success");

                } else {
                    int id = Integer.parseInt(noteId);
                    Note note = noteService.getNoteById(id);

                    noteService.update(note.getId(), title, description);
                    model.addAttribute("result", "success");

                }
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("result", "fail");
            }
            model.addAttribute("notes", noteService.getAllNotes(authentication));

        }
        return "result";
    }

    @GetMapping("/note/delete/{noteId}")
    public String deleteNote (@PathVariable Integer noteId
                                , @ModelAttribute("noteForm") NoteForm noteForm
                                , Model model, Authentication authentication) {
        try {
            noteService.deleteNoteById(noteId);
            model.addAttribute("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("result", "fail");
        }
        model.addAttribute("notes", noteService.getAllNotes(authentication));

        return "result";
    }
}
