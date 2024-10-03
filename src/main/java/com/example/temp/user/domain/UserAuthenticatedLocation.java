package com.example.temp.user.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.geo.entity.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserAuthenticatedLocation extends BaseTimeEntity implements Persistable<Long> {
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

    public void updateRepresentative(long id) {
        this.representative = this.id == id;
    }

    public String getAddressDongCd() {
        return address.getDongCd();
    }

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }

}
