package com.dipankr.todoBE.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @CrossOrigin(origins = {})
    @GetMapping("/")
    public ResponseEntity<String> testRootEndpoint() {
        return new ResponseEntity<>("Todo API is running. Successfully access to root path!", HttpStatus.OK);
    }

    @CrossOrigin (origins = {})
    @GetMapping("/test")
    public ResponseEntity<String> testApiEndpoint() {
        return new ResponseEntity<>("Todo API is running..", HttpStatus.OK);
    }
}
