package com.web.localsender.controller;

import com.web.localsender.service.LogService;
import com.web.localsender.service.SftpService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {
    @Autowired
    private SftpService sftpService;
    @Autowired
    private LogService logService;

    @GetMapping
    public String redirectToUpload(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        logService.add(ip+": User was redirected to upload form");
        return "redirect:/upload";
    }

    @GetMapping("/upload")
    public String upload(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        logService.add(ip+": Upload form was opened");
        return "upload";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        try {
            sftpService.uploadFile(file);
            logService.add(ip+": File uploaded successfully!");
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception e) {
            logService.add(ip+": File upload failed: " + e.getMessage());
            return ResponseEntity.badRequest().body("File upload failed: " + e.getMessage());
        }
    }
}
