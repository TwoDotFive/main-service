package com.example.temp.user.domain.value;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum PlatformType {
    KAKAO("kakao");

    private static final Map<String, PlatformType> NAME_TO_ENUM_MAP = new HashMap<>();
    private final String name;

    static {
        for (PlatformType platformType : PlatformType.values()) {
            NAME_TO_ENUM_MAP.put(platformType.getName().toLowerCase(), platformType);
        }
    }

    public String getName() {
        return name;
    }

    public static PlatformType fromName(String name) {
        PlatformType platformType = NAME_TO_ENUM_MAP.get(name.toLowerCase());
        if (platformType == null) {
            throw new IllegalArgumentException("No enum constant with name " + name);
        }
        return platformType;
    }
}
