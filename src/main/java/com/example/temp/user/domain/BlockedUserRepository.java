package com.example.temp.user.domain;

import org.springframework.data.repository.Repository;

public interface BlockedUserRepository extends Repository<BlockedUser, Long> {
    BlockedUser save(BlockedUser blockedUser);
}
