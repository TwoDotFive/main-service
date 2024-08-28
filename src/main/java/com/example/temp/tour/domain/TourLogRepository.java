package com.example.temp.tour.domain;

import com.example.temp.common.exception.CustomException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface TourLogRepository extends Repository<TourLog, Long> {

    TourLog save(TourLog entity);

    Optional<TourLog> findById(Long id);

    default TourLog findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Invalid Tour Log Id"));
    }

    void delete(TourLog tourLog);

    // 페치 조인은 최대 1개의 OneToMany 연관 관계만 가능해서 TourScheduleMemoImage까지는 페치 조인이 불가능
    // TourScheduleMemo - TourScheduleMemoImage 간에 N+1 문제를 해결하고자 Batch Size 설정
    @Query("SELECT t FROM TourLog t " +
            "JOIN FETCH t.schedules ts " +
            "JOIN FETCH ts.tourPlace tp " +
            "JOIN FETCH ts.memo tsm " +
            "WHERE t.id = :id")
    Optional<TourLog> findByIdWithAllAssociationsForUpdate(@Param("id") Long id);

    @Query("SELECT t FROM TourLog t " +
            "JOIN FETCH t.user u " +
            "JOIN FETCH t.stadium s " +
            "JOIN FETCH t.schedules ts " +
            "JOIN FETCH ts.tourPlace tp " +
            "JOIN FETCH ts.memo tsm " +
            "WHERE t.id = :id")
    Optional<TourLog> findByIdWithAllAssociationsForRead(@Param("id") Long id);

}
