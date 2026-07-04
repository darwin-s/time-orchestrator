package com.timeorchestrator.userservice.controller;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tst")
@Log
public class TstController {
    @GetMapping
    public String test() {
        log.info("yo!");
        return "test";
    }

    @PostMapping
    public String test1() {
        log.warning("this seems bad :(");

        throw new RuntimeException("IDK -_-");
    }
}
