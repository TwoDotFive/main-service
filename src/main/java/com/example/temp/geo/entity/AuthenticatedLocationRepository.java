package com.example.temp.geo.entity;

import org.springframework.data.repository.Repository;

public interface AuthenticatedLocationRepository extends Repository<AuthenticatedLocation, Long> {
    AuthenticatedLocation save(AuthenticatedLocation location);
}
