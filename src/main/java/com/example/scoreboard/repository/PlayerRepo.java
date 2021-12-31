package com.example.scoreboard.repository;

import com.example.scoreboard.model.Players;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepo extends JpaRepository<Players, Integer> {
}
