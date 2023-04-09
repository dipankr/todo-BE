package com.dipankr.todoBE.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public ResponseEntity<String> testRootEndpoint() {
        return new ResponseEntity<>("Todo API is running. Successfully access to root path!", HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testApiEndpoint() {
        return new ResponseEntity<>("Todo API is running..", HttpStatus.OK);
    }
}
