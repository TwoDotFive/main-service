package com.example.temp.tour.domain;

import com.example.temp.common.exception.CustomException;
import com.example.temp.tour.dto.TourLogPreviewNativeDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import java.util.List;
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

    @Query(nativeQuery = true, value = "SELECT t.id as id, t.image_url as image, t.title as title, u.nickname as userNickname, u.profile_image_url as userProfileImage, s.shorten_name as stadiumName " +
            "FROM tour_log t " +
            "JOIN user u ON t.user_id = u.id " +
            "JOIN stadium s ON t.stadium_id = s.id " +
            "WHERE t.id < :lastId " +
            "ORDER BY t.id DESC " +
            "LIMIT :pageSize")
    List<TourLogPreviewNativeDto> findRecentTourLogList(@Param("lastId") long lastTourLogId, @Param("pageSize") int pageSize);

    @Query(nativeQuery = true, value = "SELECT t.id as id, t.image_url as image, t.title as title, u.nickname as userNickname, u.profile_image_url as userProfileImage, s.shorten_name as stadiumName " +
            "FROM tour_log t " +
            "JOIN user u ON t.user_id = u.id " +
            "JOIN stadium s ON t.stadium_id = s.id " +
            "WHERE t.id < :lastId AND t.stadium_id = :stadiumId " +
            "ORDER BY t.id DESC " +
            "LIMIT :pageSize")
    List<TourLogPreviewNativeDto> findRecentTourLogListByStadium(@Param("stadiumId") long stadiumId, @Param("lastId") long lastTourLogId, @Param("pageSize") int pageSize);

}
