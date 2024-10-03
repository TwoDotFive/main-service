package com.example.temp.fanpool.domain;

import com.example.temp.common.exception.CustomException;
import com.example.temp.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface FanpoolRepository extends Repository<Fanpool, Long> {
    Fanpool save(Fanpool fanpool);

    Optional<Fanpool> findById(Long id);

    default Fanpool findByIdOrElseThrow(long id) {
        return findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "Fanpool not found")
        );
    }

    @Query("SELECT f FROM Fanpool f WHERE f.departFrom.dongCd = :dongCd")
    List<Fanpool> findByDongCd(@Param("dongCd") String dongCd, Pageable pageable);

    List<Fanpool> findByHostUserOrderByCreatedAtDesc(User user, Pageable pageable);

    @Query("SELECT COUNT(f) FROM Fanpool f WHERE f.hostUser.id = :userId")
    Long countByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(f) " +
            "FROM Fanpool f " +
            "WHERE f.game.id = :gameId" +
            "   AND f.state = 'GATHER' ")
    Long countByGame(@Param("gameId") Long gameId);

    @Query("SELECT CONCAT(f.game.homeTeam.name, ' vs ', f.game.awayTeam.name)  FROM Fanpool f WHERE f.id = ?1")
    String findTeamNamesById(Long id);

    void delete(Fanpool fanpool);
}
