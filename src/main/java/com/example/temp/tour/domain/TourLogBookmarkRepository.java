package com.example.temp.tour.domain;

import com.example.temp.common.exception.CustomException;
import com.example.temp.tour.dto.BookmarkedTourLogPreviewNativeDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface TourLogBookmarkRepository extends Repository<TourLogBookmark, Long> {

    void save(TourLogBookmark entity);

    Optional<TourLogBookmark> findById(Long id);

    default TourLogBookmark findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Invalid Bookmark Id"));
    }

    boolean existsByUserIdAndTourLogId(Long userId, Long tourLogId);

    void delete(TourLogBookmark entity);

    @Query(nativeQuery = true, value = "SELECT b.id as bookmarkId, t.id as tourLogId, t.image_url as image, t.title as title, u.nickname as userNickname, u.profile_image_url as userProfileImage, s.shorten_name as stadiumName " +
            "FROM tour_log_bookmark b " +
            "JOIN tour_log t on t.id = b.tour_log_id " +
            "JOIN user u ON t.user_id = u.id " +
            "JOIN stadium s ON t.stadium_id = s.id " +
            "WHERE b.user_id = :userId AND b.id < :lastId " +
            "ORDER BY b.id DESC " +
            "LIMIT :pageSize")
    List<BookmarkedTourLogPreviewNativeDto> findUserBookmarkedTourLogPreviewList(
            @Param("userId") Long userId,
            @Param("lastId") Long lastBookmarkId,
            @Param("pageSize") int pageSize
    );
}
