package com.example.temp.common.entity;

import com.example.temp.user.domain.value.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails {

    private final long id;

    private final UserRole role;

}
