package com.example.temp.chat.domain;

import com.example.temp.chat.dto.FindChatroomListNativeDto;
import com.example.temp.common.exception.CustomException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface ChatroomRepository extends Repository<Chatroom, Long> {

    void save(Chatroom entity);

    Optional<Chatroom> findById(Long id);

    default Chatroom findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Invalid Chatroom Id"));
    }

    @Query(
            nativeQuery = true,
            value = "SELECT cr.id as roomId, " +
                    "       cr.fanpool_id as fanpoolId, " +
                    "       IF(cr.host_user_id = :userId, TRUE, FALSE) AS isHost, " +
                    "       cu.last_activity_time as lastActivityTime, " +
                    "       u.id as partnerId, " +
                    "       u.nickname as partnerNickname, " +
                    "       u.profile_image_url as partnerImage, " +
                    "       cr.teams as teams, " +
                    "       cr.last_message_content as lastMessageContent, " +
                    "       cr.last_message_time as lastMessageTime " +
                    "FROM chatroom_user cu " +
                    "JOIN chatroom cr ON cr.id = cu.room_id " +
                    "LEFT JOIN user u ON " +
                    "   CASE " +
                    "       WHEN cr.host_user_id = :userId THEN cr.guest_user_id = u.id " +
                    "       WHEN cr.guest_user_id = :userId THEN cr.host_user_id = u.id " +
                    "       ELSE FALSE " +
                    "   END " +
                    "WHERE cu.user_id = :userId " +
                    "ORDER BY cr.last_message_time DESC"
    )
    List<FindChatroomListNativeDto> findUserJoinedChatroomList(@Param("userId") long userId);
}
