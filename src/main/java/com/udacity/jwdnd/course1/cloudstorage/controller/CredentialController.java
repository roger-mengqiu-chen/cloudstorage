package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class CredentialController {
    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/credential/add")
    public String addCredential(CredentialForm credentialForm, Authentication authentication, Model model) {
        String credentialId = credentialForm.getCredentialId();
        String url = credentialForm.getUrl();
        String username = credentialForm.getUsername();
        String password = credentialForm.getPassword();

        // check each field of form
        // password can be empty
        if (url == null || url.equals("")) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", "URL can't be empty");
            return "result";
        }
        else if (username == null || username.equals("")) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", "Username can't be empty");
            return "result";
        }

        try {
            if (credentialId == null || credentialId.equals("")) {
                credentialService.add(url, username, password, authentication);
                model.addAttribute("result", "success");
            } else {
                int id = Integer.parseInt(credentialId);
                Credential c = credentialService.getCredentialById(id);
                credentialService.update(id, url, username, password);
                model.addAttribute("result", "success");
            }
            model.addAttribute("credentials", credentialService.getAllCredentialsOfUser(authentication));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("result", "fail");
        }
        return "result";
    }

    @GetMapping(value="/credential/getDecryptedCredential", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> getDecryptedCredential(@RequestParam Integer credentialId) {
        Credential credential = credentialService.getCredentialById(credentialId);
        String decryptedPassword = credentialService.decryptPassword(credential);
        Map<String, String> map = new HashMap<>();
        map.put("encryptedPassword", credential.getPassword());
        map.put("decryptedPassword", decryptedPassword);
        return map;
    }

    @GetMapping("/credential/delete/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId
                                    , @ModelAttribute("credentialForm") CredentialForm credentialForm
                                    , Model model, Authentication authentication) {
        try {
            credentialService.delete(credentialId);
            model.addAttribute("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("result", "fail");
        }
        model.addAttribute("credentials", credentialService.getAllCredentialsOfUser(authentication));
        return "result";
    }
}
