package com.example.temp.baseball.domain;

import com.example.temp.geo.entity.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Stadium {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;
    private String aliasAddr;

    public String getStadiumName() {
        return address.getBuildName();
    }
}
