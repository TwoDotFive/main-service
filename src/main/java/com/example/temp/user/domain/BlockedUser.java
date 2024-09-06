package com.example.temp.user.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockedUser extends BaseTimeEntity {
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private User targetUser;

    public static BlockedUser build(User user, User targetUser) {
        BlockedUser ret = new BlockedUser();
        ret.id = IdUtil.create();
        ret.user = user;
        ret.targetUser = targetUser;
        return ret;
    }
}
