package com.example.temp.user.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.value.Nickname;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseTimeEntity {

    @Id
    private Long id;

    @Embedded
    private Nickname nickname;

    public static Users build(Nickname nickname) {
        Users users = new Users();
        users.id = IdUtil.create();
        users.nickname = nickname;
        return users;
    }

    public String getNickname() {
        return nickname.getValue();
    }
}
