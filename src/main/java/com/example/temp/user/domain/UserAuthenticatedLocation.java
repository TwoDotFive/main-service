package com.example.temp.user.domain;

import com.example.temp.common.entity.Address;
import com.example.temp.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthenticatedLocation extends BaseTimeEntity {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Embedded
    private Address address;

    public static UserAuthenticatedLocation build(User user, Address address) {
        UserAuthenticatedLocation ret = new UserAuthenticatedLocation();
        ret.user = user;
        ret.address = address;
        return ret;
    }
}
