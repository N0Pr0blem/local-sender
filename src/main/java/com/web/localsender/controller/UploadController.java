package com.web.localsender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadController {
    @GetMapping
    public String redirectToUpload(){
        return "redirect:/upload";
    }

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }
}
