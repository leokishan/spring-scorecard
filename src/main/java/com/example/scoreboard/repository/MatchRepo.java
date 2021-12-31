package com.example.scoreboard.repository;

import com.example.scoreboard.DTO.QueriedMatchOnly;
import com.example.scoreboard.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface MatchRepo extends JpaRepository<Match, Integer> {
    @Query(
            value = "select ft.id as fid, ft.team_name as firstTeamName, st.id as sid, st.team_name as secondTeamName\n" +
                    "from match_played inner join team as ft on match_played.first_team = ft.id\n" +
                    "                  inner join team as st on match_played.second_team = st.id\n" +
                    "where first_team=?1 or second_team=?1",
            nativeQuery = true
    )
    Collection<QueriedMatchOnly> getQueriedList(int id);
}
