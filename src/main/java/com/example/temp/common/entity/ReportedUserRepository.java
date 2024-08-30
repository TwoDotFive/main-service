package com.example.temp.common.entity;


import org.springframework.data.repository.Repository;

public interface ReportedUserRepository extends Repository<ReportedUser, Long> {
    ReportedUser save(ReportedUser report);
}
