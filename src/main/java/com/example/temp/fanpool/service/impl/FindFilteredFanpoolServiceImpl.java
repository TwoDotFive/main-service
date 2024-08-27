package com.example.temp.fanpool.service.impl;

import com.example.temp.fanpool.domain.Fanpool;
import com.example.temp.fanpool.domain.QFanpool;
import com.example.temp.fanpool.domain.value.FanpoolState;
import com.example.temp.fanpool.dto.FindFilteredFanpoolResponse;
import com.example.temp.fanpool.dto.command.FindFilteredFanpoolCommand;
import com.example.temp.fanpool.service.FindFilteredFanpoolService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindFilteredFanpoolServiceImpl implements FindFilteredFanpoolService {
    private final JPAQueryFactory queryFactory;

    @Override
    public FindFilteredFanpoolResponse doService(FindFilteredFanpoolCommand command) {
        QFanpool fanpool = QFanpool.fanpool;
        BooleanBuilder builder = new BooleanBuilder();

        if (command.gameId() != null && !command.gameId().isEmpty()) {
            builder.and(fanpool.game.id.in(command.gameId()));
        }
        if (command.departAt() != null) {
            builder.and(
                    fanpool.departAt.year().eq(command.departAt().getYear())
                            .and(fanpool.departAt.month().eq(command.departAt().getMonthValue()))
                            .and(fanpool.departAt.dayOfMonth().eq(command.departAt().getDayOfMonth()))
            );
        }
        if (command.teamId() != null) {
            builder.and(
                    (fanpool.game.awayTeam.id.eq(command.teamId()))
                            .or(fanpool.game.homeTeam.id.eq(command.teamId()))
            );
        }
        if (command.dongCd() != null) {
            builder.and(fanpool.departFrom.dongCd.eq(command.dongCd()));
        }
        if (command.onlyGathering()) {
            builder.and(fanpool.state.eq(FanpoolState.GATHER));
        }

        List<Fanpool> result = queryFactory.selectFrom(fanpool)
                .where(builder)
                .offset(command.pageable().getOffset())
                .limit(command.pageable().getPageSize() + 1L)
                .fetch();

        return FindFilteredFanpoolResponse.build(result);
    }
}
