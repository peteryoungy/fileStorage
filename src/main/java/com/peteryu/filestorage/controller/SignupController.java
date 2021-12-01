package com.peteryu.filestorage.controller;

import com.peteryu.filestorage.model.User;
import com.peteryu.filestorage.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.peteryu.filestorage.constant.ConsMsg.*;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignupPage(@ModelAttribute("User") User user, Model model){

        model.addAttribute("pwdErrMsg", "");
        return "signup";
    }

    @PostMapping
    public String postDealer(@Valid @ModelAttribute("User") User user, Model model,
                             RedirectAttributes redirectAttributes){

//        if (bindingResult.hasErrors()) {
//            return "signup";
//        }
        String validMsg = userService.PasswordValid(user);
        model.addAttribute("pwdErrMsg", validMsg);

        if(validMsg != ""){
            return "signup";
        }

        String signupErr = null;

        userService.hashUserPassword(user);


        if(userService.isDuplicate(user.getUserName())){
            signupErr = SIGNUP_USER_EXISTING_ERR;
        }

        if(signupErr == null){
            int id = userService.insertUser(user);
            if(id < 0){
                signupErr = SIGNUP_FAILED_ERR;
            }
        }

        if(signupErr==null){
            redirectAttributes.addAttribute("isSuccess",true);
            redirectAttributes.addAttribute("signupMsg",SIGNUP_SUCCESS + user.getUserName());
            return "redirect:/login";
        }
        else{
            model.addAttribute("isFailure",true);
            model.addAttribute("signupMsg",signupErr);
        }

        return "signup";
    }

}
