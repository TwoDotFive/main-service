package com.example.temp.user.domain;

import org.springframework.data.repository.Repository;

public interface UserAuthenticatedLocationRepository extends Repository<UserAuthenticatedLocation, Long> {
    void save(UserAuthenticatedLocation save);
}
