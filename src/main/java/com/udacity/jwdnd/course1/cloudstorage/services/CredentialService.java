package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;
    private final UserMapper userMapper;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService, UserMapper userMapper) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.userMapper = userMapper;
    }

    public List<Credential> getAllCredentialsOfUser(Authentication authentication) {
        User user = userMapper.getUser(authentication.getName());
        List<Credential> credentials = credentialMapper.getCredentialOfUser(user.getUserId());

        return credentials;
    }

    public Credential getCredentialById(Integer id) {
        Credential credential =  credentialMapper.getCredentialById(id);

        return credential;
    }

    public String decryptPassword (Credential credential) {
        String key = credential.getKey();
        String encryptedPassword = credential.getPassword();
        String decryptedPassword = encryptionService.decryptValue(encryptedPassword, key);
        return decryptedPassword;
    }

    public void delete (Integer id) {
        credentialMapper.deleteCredentialById(id);
    }

    public void add (String url, String username, String password, Authentication authentication) {
        Credential credential = new Credential();
        credential.setUrl(url);
        credential.setUsername(username);
        User user = userMapper.getUser(authentication.getName());
        credential.setUserId(user.getUserId());
        // generate key
        String encodedKey = generateKey();
        credential.setKey(encodedKey);
        // encrypt password
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        credential.setPassword(encryptedPassword);
        credentialMapper.addCredential(credential);
    }

    public void update (Integer credentialId, String url, String username, String password) {
        Credential credential = credentialMapper.getCredentialById(credentialId);
        // generate key
        String encodedKey = generateKey();
        // encrypt password
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        credentialMapper.updateCredential(credentialId, url, username, encodedKey, encryptedPassword);
    }

    private String generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
