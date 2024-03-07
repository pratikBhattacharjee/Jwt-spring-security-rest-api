package com.pratik.Jwtspringsecurityrestapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class DemoControllers {
    @GetMapping("/sample")
    public ResponseEntity<String> secured() {
        return ResponseEntity.ok("This is a secured endpoint");
    }
}
