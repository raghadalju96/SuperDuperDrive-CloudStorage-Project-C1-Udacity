package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class CredentialController {

    private CredentialService credentialService;

    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService){
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/addCredential")
    public String addCredential(Credential credential, Authentication authentication, Model model) throws IOException {
        String userName = authentication.getName();
        User user = userService.getUser(userName);

        if(credential.getCredentialId() == null ){
            try {
                this.credentialService.addCredential(credential , user.getUserId());
                return "redirect:/result?success";
            }
            catch (Exception e){
                return "redirect:/result?error";
            }
        }
        else{
            try {
                this.credentialService.updateCredential(credential);
                return "redirect:/result?success";
            }
            catch (Exception e){
                System.out.println(e);
                return "redirect:/result?error";
            }}
    }

    @RequestMapping("/deleteCredential/{credentialId}")
    public String deleteCredential(@PathVariable int credentialId , Model model) throws IOException{

        try{
            this.credentialService.deleteCredential(credentialId);
            return "redirect:/result?success";
        }
        catch (Exception e){
            return "redirect:/result?error";
        }


    }
}
