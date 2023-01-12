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
                model.addAttribute("credentialSuccess" , "");
            }
            catch (Exception e){
                model.addAttribute("credentialSuccess" , "");
            }
        }
        else{
            try {
                this.credentialService.updateCredential(credential);
                model.addAttribute("noteUploadSuccess", "true");
            }
            catch (Exception e){
               // noteUploadError = e.toString();
                model.addAttribute("noteError", "noteUploadError");
            }}

        this.credentialService.getCredentials(user.getUserId());
        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        System.out.println( this.credentialService.getCredentials(user.getUserId()));
        System.out.println("anghaaam");
        System.out.println(credential.getUserName());
        return "result";
    }

    @RequestMapping("/deleteCredential/{credentialId}")
    public String deleteCredential(@PathVariable int credentialId , Model model) throws IOException{

        try{
            this.credentialService.deleteCredential(credentialId);
        }
        catch (Exception e){
         //   noteDeleteError = e.toString();
            model.addAttribute("noteDeleteError", "noteDeleteError");
        }

        return "result";
    }
}
