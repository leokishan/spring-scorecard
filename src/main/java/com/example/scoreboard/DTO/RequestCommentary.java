package com.example.scoreboard.DTO;

public class RequestCommentary {
    public int match_id;
    public String dialogue;
    public String ballCount;

    @Override
    public String toString() {
        return "RequestCommentary{" +
                "match_id=" + match_id +
                ", dialogue='" + dialogue + '\'' +
                ", ballCount='" + ballCount + '\'' +
                '}';
    }
}
