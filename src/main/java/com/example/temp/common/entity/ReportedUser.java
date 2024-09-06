package com.example.temp.common.entity;

import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportedUser extends BaseTimeEntity {
    @Id
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private User targetUser;
    private String content;

    public static ReportedUser build(User user, User targetUser, String content) {
        ReportedUser ret = new ReportedUser();
        ret.id = IdUtil.create();
        ret.user = user;
        ret.targetUser = targetUser;
        ret.content = content;
        return ret;
    }
}
