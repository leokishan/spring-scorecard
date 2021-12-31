package com.example.scoreboard.controller;

import com.example.scoreboard.DTO.CommentaryResponse;
import com.example.scoreboard.DTO.RequestCommentary;
import com.example.scoreboard.model.Commentary;
import com.example.scoreboard.service.CommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CommentaryController {

    @Autowired
    CommentaryService commentaryService;

    @PostMapping(path = "/commentary")
    public Commentary createCommentary(@RequestBody RequestCommentary requestCommentary) throws Exception {
        return commentaryService.addCommentary(requestCommentary);
    }

    @PostMapping(path = "/commentary/publish")
    public Map publishCommentary(@RequestBody RequestCommentary requestCommentary) {
        return commentaryService.publishCommentary(requestCommentary);
    }

    @GetMapping(path = "/commentary")
    public List<CommentaryResponse> listCommentary() {
        return commentaryService.listCommentary();
    }
}
