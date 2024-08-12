package com.example.temp.user.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.geo.entity.AuthenticatedLocation;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthenticatedLocation extends BaseTimeEntity {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private AuthenticatedLocation firstLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    private AuthenticatedLocation secondLocation;

    public static UserAuthenticatedLocation build(User user, AuthenticatedLocation firstLocation) {
        UserAuthenticatedLocation ret = new UserAuthenticatedLocation();
        ret.id = IdUtil.create();
        ret.user = user;
        ret.firstLocation = firstLocation;
        return ret;
    }
}
