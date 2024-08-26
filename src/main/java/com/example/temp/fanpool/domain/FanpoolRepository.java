package com.example.temp.fanpool.domain;

import com.example.temp.common.exception.CustomException;
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
}
