package com.peteryu.filestorage.controller;

import com.peteryu.filestorage.model.File;
import com.peteryu.filestorage.service.FileService;
import com.peteryu.filestorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

//    public HomeController(FileService fileService, UserService userService) {
//        this.fileService = fileService;
//        this.userService = userService;
//    }

    @GetMapping
    public String getHome(Authentication authentication, Model model){

        // return "home";
//        String userName = authentication.getName();
//        List<File> fileList = null;
//
//        if(userName != null){
//            int userId = userService.getUser(userName).getUserId();
//            fileList = fileService.getAllFilesForUser(userId);
//        }

        String userName = authentication.getName();
        int userId = userService.getUser(userName).getUserId();
        List<File> fileList = fileService.getAllFilesForUser(userId);

        model.addAttribute("fileList",fileList);
        return "home";
    }

//    @ModelAttribute("fileList")
//    public List<File> getFile(int userId){
//        return fileService.getAllFilesForUser(userId);
//    }

}
