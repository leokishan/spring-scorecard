package com.example.scoreboard.model;

import javax.persistence.*;

@Entity
@Table(name = "match_played")
public class Match {

    public Match() {
    }

    public Match(Team firstTeam, Team secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "first_team")
    public Team firstTeam;

    @ManyToOne
    @JoinColumn(name = "second_team")
    public Team secondTeam;
}
