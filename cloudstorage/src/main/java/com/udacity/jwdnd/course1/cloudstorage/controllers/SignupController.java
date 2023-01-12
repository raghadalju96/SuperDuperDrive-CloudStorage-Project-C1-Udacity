package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signUpView(){
        return "signup";
    }

    @PostMapping()
    public String signup(User user, Model model){

        String signupError = null;

        if(!this.userService.isUsernameAvailable(user.getUsername())){
            signupError = "The username already exists.";
        }

        if(signupError == null){
            int rowsAdded =   this.userService.createUser(user);
            if(rowsAdded < 0){
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
            return "redirect:login";
        } else {
            model.addAttribute("signupError", signupError);
            return "signup";
        }



    }
}
