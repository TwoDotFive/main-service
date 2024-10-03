package com.example.temp.fanpool.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface FanpoolUserRepository extends Repository<FanpoolUser, Long> {

    FanpoolUser save(FanpoolUser fanpoolUser);

    void deleteByFanpoolId(long fanpoolId);

    void deleteByFanpoolIdAndUserId(long fanpoolId, long userId);

    boolean existsByFanpoolIdAndUserId(long fanpoolId, long userId);

    Optional<FanpoolUser> findByFanpoolIdAndUserId(long fanpoolId, long user);
}
