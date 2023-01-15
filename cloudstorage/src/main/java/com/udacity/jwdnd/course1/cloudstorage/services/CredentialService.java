package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CredentialService {


    private EncryptionService encryptionService;
    private CredentialMapper credentialMapper;

    public CredentialService(EncryptionService encryptionService, CredentialMapper credentialMapper) {
        this.encryptionService = encryptionService;
        this.credentialMapper = credentialMapper;
    }


    public int addCredential(Credential credential, int userId){

        credential.setUrl(credential.getUrl());
        credential.setUserName(credential.getUserName());
        credential.setPassword(this.encryptionService.encryptValue(credential.getPassword()));
        credential.setUserId(userId);
        return this.credentialMapper.insertCredential(credential);
    }

    public void updateCredential(Credential credential){

        credential.setUrl(credential.getUrl());
        credential.setUserName(credential.getUserName());
        credential.setPassword(this.encryptionService.encryptValue(credential.getPassword()));
        this.credentialMapper.updateCredential(credential);
    }

    public List<Credential> getCredentials(int userId){
        return this.credentialMapper.getCredentials(userId);
    }

    public Map<String, String> getDecryptPassword(int credentialId){

      Credential credential =  this.credentialMapper.getCredential(credentialId);
        String decryptedPassword = this.encryptionService.decryptValue(credential.getPassword());
        Map<String, String> response = new HashMap<>();
        response.put("decryptedPassword", decryptedPassword);
        return response;
    }

    public void deleteCredential(int credentialId){
        this.credentialMapper.deleteCredential(credentialId);
    }
}
