package com.example.temp.user.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface BlockedUserRepository extends Repository<BlockedUser, Long> {
    BlockedUser save(BlockedUser blockedUser);

    Optional<BlockedUser> findByUserAndTargetUser(User user, User targetUser);

    void delete(BlockedUser blockedUser);
}
