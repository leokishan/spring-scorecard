package com.example.scoreboard.model;

import javax.persistence.*;

@Entity
@Table(name = "match_commentary")
public class Commentary {

    public Commentary() {}

    public Commentary(Match match, String dialogue, String ballCount) {
        this.match = match;
        this.dialogue = dialogue;
        this.ballCount = ballCount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    public Match match;

    public String dialogue;
    public String ballCount;
}
