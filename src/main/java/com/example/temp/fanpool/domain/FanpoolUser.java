package com.example.temp.fanpool.domain;

import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(EnableJpaAuditing.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FanpoolUser implements Persistable<Long> {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Fanpool fanpool;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreatedDate
    private LocalDateTime createdAt;

    public static FanpoolUser build(Fanpool fanpool, User user) {
        FanpoolUser ret = new FanpoolUser();
        ret.id = IdUtil.create();
        ret.fanpool = fanpool;
        ret.user = user;
        return ret;
    }

    @Override
    public boolean isNew() {
        return createdAt == null;
    }
}
