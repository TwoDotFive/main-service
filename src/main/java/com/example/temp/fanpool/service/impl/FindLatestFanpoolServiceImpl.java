package com.example.temp.fanpool.service.impl;

import com.example.temp.baseball.domain.Team;
import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.fanpool.domain.QFanpool;
import com.example.temp.fanpool.dto.FindLatestFanpoolResponse;
import com.example.temp.fanpool.service.FindLatestFanpoolService;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindLatestFanpoolServiceImpl implements FindLatestFanpoolService {
    private static final int FIND_LIMIT_NUMBER = 4;

    private final UserRepository userRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public FindLatestFanpoolResponse doService(long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        Team favoriteTeam = user.getFavoriteTeam();

        QFanpool fanpool = QFanpool.fanpool;
        BooleanBuilder builder = new BooleanBuilder();

        if (favoriteTeam != null) {
            builder.and((fanpool.game.awayTeam.eq(favoriteTeam))
                    .or(fanpool.game.homeTeam.eq(favoriteTeam))
            );
        }

        List<Fanpool> found = queryFactory.selectFrom(fanpool)
                .where(builder)
                .orderBy(fanpool.departAt.desc())
                .limit(FIND_LIMIT_NUMBER)
                .fetch();

        return FindLatestFanpoolResponse.build(found);
    }
}
