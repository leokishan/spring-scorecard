package com.example.scoreboard.repository;

import com.example.scoreboard.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Team, Integer> {
    Team findByTeamName(String teamName);
}
