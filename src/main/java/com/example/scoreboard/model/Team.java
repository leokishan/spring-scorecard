package com.example.scoreboard.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {

    public Team() {
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String teamName;

    @OneToMany(mappedBy = "team")
    public List<Players> players;
}
