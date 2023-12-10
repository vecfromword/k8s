package com.emailapp.demo1.controller;

import com.emailapp.demo1.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestParam String to, 
                                            @RequestParam String body) {
        emailService.sendSimpleMessage(to, body);
        return ResponseEntity.ok("Email sent successfully");
    }
}
