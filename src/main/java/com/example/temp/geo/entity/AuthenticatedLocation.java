package com.example.temp.geo.entity;

import com.example.temp.common.entity.Address;
import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AuthenticatedLocation extends BaseTimeEntity {
    @Id
    private Long id;

    @Embedded
    private Address address;

    public AuthenticatedLocation(Address address) {
        this.id = IdUtil.create();
        this.address = address;
    }
}
