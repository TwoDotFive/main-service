package com.example.temp.geo.dto;

import com.example.temp.geo.entity.Address;

public record AddressInformationView(
        String fullText,
        String zipNo,
        String sido,
        String sigungu,
        String dong,
        String dongCd,
        String x,
        String y
) {
    public AddressInformationView(Address address) {
        this(address.getFullText(), address.getZipNo(), address.getSido(), address.getSigungu(), address.getDong(), address.getDongCd(), address.getX(), address.getY());
    }

    public static AddressInformationView of(Address address) {
        return new AddressInformationView(
                address.getFullText(),
                address.getZipNo(),
                address.getSido(),
                address.getSigungu(),
                address.getDong(),
                address.getDongCd(),
                address.getX(),
                address.getY());
    }

    public Address toEntity() {
        return Address.builder()
                .fullText(fullText)
                .zipNo(zipNo)
                .sido(sido)
                .sigungu(sigungu)
                .dong(dong)
                .dongCd(dongCd)
                .x(x)
                .y(y)
                .build();
    }
}
