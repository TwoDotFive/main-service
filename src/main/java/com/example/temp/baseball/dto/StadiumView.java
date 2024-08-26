package com.example.temp.baseball.dto;

import com.example.temp.baseball.domain.Stadium;
import com.example.temp.geo.dto.AddressInformationView;

public record StadiumView(String shortenName, AddressInformationView address) {

    public static StadiumView of(Stadium stadium) {
        return new StadiumView(
                stadium.getShortenName(),
                AddressInformationView.of(stadium.getAddress()));
    }
}