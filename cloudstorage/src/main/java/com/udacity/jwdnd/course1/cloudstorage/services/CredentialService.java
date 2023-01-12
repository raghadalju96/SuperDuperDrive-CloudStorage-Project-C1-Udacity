package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {


    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }


    public int addCredential(Credential credential, int userId){
        credential.setUrl(credential.getUrl());
        credential.setUserName(credential.getUserName());
        credential.setPassword(credential.getPassword());
        credential.setUserId(userId);
        return this.credentialMapper.insertCredential(credential);
    }

    public Credential updateCredential(Credential credential){
        credential.setUrl(credential.getUrl());
        credential.setUserName(credential.getUserName());
        credential.setPassword(credential.getPassword());

        return this.credentialMapper.updateCredential(credential);
    }

    public List<Credential> getCredentials(int userId){
        return this.credentialMapper.getCredentials(userId);
    }

    public void deleteCredential(int credentialId){
        this.credentialMapper.deleteCredential(credentialId);
    }
}
