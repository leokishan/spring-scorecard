package com.example.scoreboard.DTO;

import org.springframework.beans.factory.annotation.Value;

public interface QueriedMatchOnly {
    @Value("#{new com.example.scoreboard.DTO.PlayerTeam(target.fid, target.firstTeamName)}")
    PlayerTeam getFirstTeam();

    @Value("#{new com.example.scoreboard.DTO.PlayerTeam(target.sid, target.secondTeamName)}")
    PlayerTeam getSecondTeam();
}
