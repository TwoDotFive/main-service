package com.example.temp.fanpool.domain;

import com.example.temp.common.util.IdUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
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

    private Long fanpoolId;

    private Long userId;

    @CreatedDate
    private LocalDateTime createdAt;

    public static FanpoolUser build(Long fanpoolId, Long userId) {
        FanpoolUser ret = new FanpoolUser();
        ret.id = IdUtil.create();
        ret.fanpoolId = fanpoolId;
        ret.userId = userId;
        return ret;
    }

    @Override
    public boolean isNew() {
        return createdAt == null;
    }
}
