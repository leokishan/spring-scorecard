package com.example.scoreboard.controller;

import com.example.scoreboard.DTO.PlayerResponse;
import com.example.scoreboard.DTO.RequestPlayer;
import com.example.scoreboard.DTO.RequestTeam;
import com.example.scoreboard.DTO.TeamResponse;
import com.example.scoreboard.model.Players;
import com.example.scoreboard.model.Team;
import com.example.scoreboard.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping(path = "/player")
    public Players addPlayer(@RequestBody RequestPlayer requestPlayer) {
        try {
            return teamService.addPlayer(requestPlayer);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(path = "/team")
    public Team addTeam(@RequestBody RequestTeam team) {
        return teamService.addTeam(team);
    }

    @GetMapping(path = "/teams")
    public List<TeamResponse> listTeams() {
        return teamService.listTeam();
    }

    @GetMapping(path = "/players")
    public List<PlayerResponse> listPlayers() {
        return teamService.listPlayer();
    }
}
