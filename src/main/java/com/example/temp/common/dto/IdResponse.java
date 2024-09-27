package com.example.temp.common.dto;

public record IdResponse(String id) {

    public static IdResponse build(Long id) {
        return new IdResponse(id.toString());
    }
}
