package com.example.temp.geo.controller;

import com.example.temp.common.entity.Address;
import com.example.temp.geo.service.impl.Coord2AddressClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geo")
@RequiredArgsConstructor
public class GeoController {
    private final Coord2AddressClient coord2AddressClient;

    @GetMapping("/coord2address")
    public ResponseEntity<?> trans(
            @RequestParam("x") String x,
            @RequestParam("y") String y
    ) {
        Address address = coord2AddressClient.trans(x, y);
        return ResponseEntity.ok(address);
    }
}
