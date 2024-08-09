package com.example.temp.user.dto;

import com.example.temp.common.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserAuthenticatedLocationRequest {
    private String x;
    private String y;
    private String fullText;
    private String zipNo;
    private String sido;
    private String sigungu;
    private String dong;

    public Address toEntity() {
        return Address.builder()
                .x(x)
                .y(y)
                .fullText(fullText)
                .zipNo(zipNo)
                .sido(sido)
                .sigungu(sigungu)
                .dong(dong)
                .build();
    }
}
