package com.example.temp.fanpool.service.impl;

import com.example.temp.baseball.domain.Game;
import com.example.temp.baseball.domain.GameRepository;
import com.example.temp.common.exception.CustomException;
import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.fanpool.domain.FanpoolRepository;
import com.example.temp.fanpool.dto.command.UpdateFanpoolCommand;
import com.example.temp.fanpool.service.UpdateFanpoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateFanpoolServiceImpl implements UpdateFanpoolService {
    private final FanpoolRepository fanpoolRepository;
    private final GameRepository gameRepository;

    @Override
    public void doService(UpdateFanpoolCommand command) {
        Fanpool target = fanpoolRepository.findByIdOrElseThrow(command.getFanpoolId());

        if (target.isNotHostUser(command.getUserId())) {
            throw new CustomException(HttpStatus.FORBIDDEN, "호스트 유저만 변경 가능합니다");
        }

        target.updateInfo(command.getBody());

        Game game = gameRepository.findByIdOrElseThrow(Long.parseLong(command.getBody().getGameId()));
        target.updateGame(game);
    }
}
