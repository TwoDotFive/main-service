package com.example.temp.geo.entity;

import org.springframework.data.repository.Repository;

public interface AddressRepository extends Repository<Address, Long> {
    Address save(Address address);
}
