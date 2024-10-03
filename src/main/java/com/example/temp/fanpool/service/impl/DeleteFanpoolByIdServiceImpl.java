package com.example.temp.fanpool.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.fanpool.domain.FanpoolRepository;
import com.example.temp.fanpool.domain.FanpoolUserRepository;
import com.example.temp.fanpool.service.DeleteFanpoolByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteFanpoolByIdServiceImpl implements DeleteFanpoolByIdService {

    private final FanpoolRepository fanpoolRepository;
    private final FanpoolUserRepository fanpoolUserRepository;

    @Override
    @Transactional
    public void doService(long userId, long fanpoolId) {
        Fanpool fanpool = fanpoolRepository.findByIdOrElseThrow(fanpoolId);

        if (fanpool.isNotHostUser(userId)) {
            throw new CustomException(HttpStatus.FORBIDDEN, "주최한 유저만 삭제 가능합니다");
        }

        fanpoolUserRepository.deleteByFanpoolId(fanpoolId);
        fanpoolRepository.deleteById(fanpoolId);
    }
}
