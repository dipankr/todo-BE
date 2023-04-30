package com.dipankr.todobe.controller;

import static com.dipankr.todobe.wrapper.ResponseWrapper.getResponseJson;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestController {

    /**
     * Endpoint to test the status of the server
     *
     * @return a message indicating that the server is up
     */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> testRootEndpoint() {
        return getResponseJson(null, null, "Todo API is up and running.", HttpStatus.OK);
    }

    /**
     * Endpoint to test the status of the server <br/> Indicates that paths other than root are accessible
     *
     * @return a message indicating that server is up and different paths are accessible
     */
    @GetMapping(value = "/test", produces = "application/json")
    public ResponseEntity<?> testApiEndpoint() {
        return getResponseJson(null, null, "/test is responsive..", HttpStatus.OK);
    }
}
