package com.example.scoreboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {
    @GetMapping(path = "/ping")
    public String ping() {
        return "Ping!";
    }
}
