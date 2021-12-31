package com.example.scoreboard.model;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Players {

    public Players() {

    }

    public Players(String name, Team team) {
        this.playerName = name;
        this.team = team;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String playerName;

    @ManyToOne
    @JoinColumn(name = "team_id")
    public Team team;

}
