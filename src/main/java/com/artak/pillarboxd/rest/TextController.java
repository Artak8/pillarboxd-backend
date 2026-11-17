package com.artak.pillarboxd.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {
    // Test returning strings to the frontend
    @GetMapping("/api/rest/text")
    public String returnString() {
        return "Hello World!";
    }
}