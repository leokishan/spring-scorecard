package com.example.scoreboard.service;

import com.example.scoreboard.DTO.*;
import com.example.scoreboard.model.Commentary;
import com.example.scoreboard.model.Match;
import com.example.scoreboard.model.Players;
import com.example.scoreboard.model.Team;
import com.example.scoreboard.repository.CommentaryRepo;
import com.example.scoreboard.repository.MatchRepo;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentaryService {
    @Autowired
    CommentaryRepo commentaryRepo;

    @Autowired
    MatchRepo matchRepo;

    @Autowired
    KafkaTemplate<String, RequestCommentary> kafkaTemplate;

    @Value("${kafkaproperties.topic}")
    String kafkaTopic;

    public Commentary addCommentary(RequestCommentary requestCommentary) throws Exception {
        Optional<Match> match = matchRepo.findById(requestCommentary.match_id);
        if (match.isPresent()) {
            Commentary commentary = new Commentary(match.get(), requestCommentary.dialogue, requestCommentary.ballCount);
            return commentaryRepo.save(commentary);
        } else {
            throw new NoSuchElementException("Invalid match id!");
        }
    }

    public Map publishCommentary(RequestCommentary requestCommentary) {
        Optional<Match> match = matchRepo.findById(requestCommentary.match_id);
        Map<String, Boolean> response = new HashMap<>();
        if (match.isPresent()) {
            response.put("success", true);
            try {
                kafkaTemplate.send(kafkaTopic, requestCommentary).get();
            } catch (Exception e) {
                response.put("success", false);
                e.printStackTrace();
            }
        } else {
            response.put("success", false);
        }
        return response;
    }

    @KafkaListener(
            topics = "${kafkaproperties.topic}",
            groupId = "${kafkaproperties.groupid}"
    )
    public void listenCommentary(RequestCommentary requestCommentary) {
        System.out.println("In listener --------------------------------");
        Optional<Match> match = matchRepo.findById(requestCommentary.match_id);
        if(requestCommentary.dialogue.contains("bad_word")) {
            requestCommentary.dialogue = requestCommentary.dialogue.replaceAll("bad_word", "*****");
        }
        Commentary commentary = new Commentary(match.get(), requestCommentary.dialogue, requestCommentary.ballCount);
        commentaryRepo.save(commentary);
    }

    public List<CommentaryResponse> listCommentary() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Commentary.class, CommentaryResponse.class);
//        mapperFactory.classMap(Match.class, Integer.class);
        List<Commentary> commentaryList = commentaryRepo.findAll();
        List<CommentaryResponse> commentaryResponseList = mapperFactory.getMapperFacade()
                .mapAsList(commentaryList, CommentaryResponse.class);
        return commentaryResponseList;
    }
}
