package com.cm.bbuserapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/testOne")
    public ResponseEntity<String> testOne(){
        return new ResponseEntity<>("Test One", HttpStatus.OK);
    }
}
