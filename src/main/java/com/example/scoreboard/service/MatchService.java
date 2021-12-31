package com.example.scoreboard.service;

import com.example.scoreboard.DTO.MatchResponse;
import com.example.scoreboard.DTO.QueriedMatchOnly;
import com.example.scoreboard.DTO.RequestMatch;
import com.example.scoreboard.model.Match;
import com.example.scoreboard.model.Team;
import com.example.scoreboard.repository.MatchRepo;
import com.example.scoreboard.repository.TeamRepo;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MatchService {
    @Autowired
    MatchRepo matchRepo;

    @Autowired
    TeamRepo teamRepo;

    public List<MatchResponse> matchList() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        return mapperFactory.getMapperFacade().mapAsList(matchRepo.findAll(), MatchResponse.class);
    }

    public MatchResponse addMatch(RequestMatch requestMatch) throws Exception {
        Optional<Team> team1 = teamRepo.findById(requestMatch.team1);
        Optional<Team> team2 = teamRepo.findById(requestMatch.team2);
        if (team1.isPresent() && team2.isPresent()) {
            Match match = new Match(team1.get(), team2.get());
            Match match1 = matchRepo.save(match);
            MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

            return mapperFactory.getMapperFacade().map(match1, MatchResponse.class);
        } else {
            throw new NoSuchElementException("Invalid team ids!");
        }
    }

    public Collection<QueriedMatchOnly> getQueriedMatches() {
        return matchRepo.getQueriedList(2);
    }
}
