package com.example.scoreboard.service;

import com.example.scoreboard.DTO.*;
import com.example.scoreboard.model.Players;
import com.example.scoreboard.model.Team;
import com.example.scoreboard.repository.PlayerRepo;
import com.example.scoreboard.repository.TeamRepo;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TeamService {
    @Autowired
    TeamRepo teamRepo;

    @Autowired
    PlayerRepo playerRepo;

    public Players addPlayer(RequestPlayer requestPlayer) throws NoSuchElementException {
        Team team = teamRepo.findByTeamName(requestPlayer.teamName);
        if (team != null) {
            Players players = new Players(requestPlayer.playerName, team);
            players = playerRepo.save(players);
            return players;
        } else {
            throw new NoSuchElementException("No such team found.");
        }
    }

    public Team addTeam(RequestTeam requestTeam) {
        Team team = new Team(requestTeam.name);
        team = teamRepo.save(team);
        return team;
    }

    public List<TeamResponse> listTeam() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
//        mapperFactory.classMap(Team.class, TeamResponse.class)
//                .field("players{id}", "players{}").byDefault().register();
        mapperFactory.classMap(Team.class, TeamResponse.class)
                .byDefault().register();
        mapperFactory.classMap(Players.class, TeamPlayer.class)
                .byDefault().register();
        List<Team> teamList = teamRepo.findAll();
        List<TeamResponse> teamResponseList = mapperFactory.getMapperFacade()
                .mapAsList(teamList, TeamResponse.class);
        return teamResponseList;
    }

    public List<PlayerResponse> listPlayer() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Team.class, PlayerTeam.class).byDefault().register();
        return mapperFactory.getMapperFacade().mapAsList(playerRepo.findAll(), PlayerResponse.class);
    }
}
