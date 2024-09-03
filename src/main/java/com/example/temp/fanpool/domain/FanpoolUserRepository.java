package com.example.temp.fanpool.domain;

import com.example.temp.user.domain.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface FanpoolUserRepository extends Repository<FanpoolUser, Long> {
    FanpoolUser save(FanpoolUser fanpoolUser);

    void deleteByUserAndFanpool(User user, Fanpool fanpool);

    Optional<FanpoolUser> findByFanpoolAndUser(Fanpool fanpool, User user);
}
