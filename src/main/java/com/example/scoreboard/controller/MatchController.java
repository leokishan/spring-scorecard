package com.example.scoreboard.controller;

import com.example.scoreboard.DTO.MatchResponse;
import com.example.scoreboard.DTO.QueriedMatchOnly;
import com.example.scoreboard.DTO.RequestMatch;
import com.example.scoreboard.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@RestController
public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping(path = "/matches")
    public List<MatchResponse> getMatchList() {
        return matchService.matchList();
    }

    @PostMapping(path = "/match")
    public MatchResponse addMatch(@RequestBody RequestMatch requestMatch) {
        try {
            return matchService.addMatch(requestMatch);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(path = "/queriedMatch")
    public Collection<QueriedMatchOnly> queriedMatch() {
        return matchService.getQueriedMatches();
    }
}
