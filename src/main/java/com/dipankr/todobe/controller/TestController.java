package com.dipankr.todobe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dipankr.todobe.service.ResponseService.getResponseJson;

@RestController
public class TestController {
    @CrossOrigin
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> testRootEndpoint() {
        return getResponseJson(null, null, "Todo API is up and running.", HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/test", produces = "application/json")
    public ResponseEntity<?> testApiEndpoint() {
        return getResponseJson(null, null, "/test is responsive..", HttpStatus.OK);
    }
}
