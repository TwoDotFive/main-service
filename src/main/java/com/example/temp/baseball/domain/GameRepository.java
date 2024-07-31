package com.example.temp.baseball.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface GameRepository extends Repository<Game, Long> {
    @Query("SELECT g FROM Game g WHERE g.season = :season AND (g.homeTeam = :team OR g.awayTeam = :team) AND g.startDate >= :startOfWeek AND g.startDate < :endOfWeek")
    List<Game> findAllByTeamAndCurrentWeekInYear(@Param("season") Season season,
                                                 @Param("team") Team team,
                                                 @Param("startOfWeek") LocalDateTime startOfWeek,
                                                 @Param("endOfWeek") LocalDateTime endOfWeek);
}
