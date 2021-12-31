package com.example.scoreboard.repository;

import com.example.scoreboard.model.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaryRepo extends JpaRepository<Commentary, Integer> {
}
