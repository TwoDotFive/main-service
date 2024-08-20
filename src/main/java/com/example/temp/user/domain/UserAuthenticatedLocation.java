package com.example.temp.user.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.geo.entity.Address;
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
    private Address address;

    private boolean representative;

    public static UserAuthenticatedLocation build(User user, Address address, boolean representative) {
        UserAuthenticatedLocation ret = new UserAuthenticatedLocation();
        ret.id = IdUtil.create();
        ret.user = user;
        ret.address = address;
        ret.representative = representative;
        return ret;
    }

    public String getAddressDongCd() {
        return address.getDongCd();
    }
}
