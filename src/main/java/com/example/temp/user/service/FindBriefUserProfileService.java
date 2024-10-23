package com.example.temp.user.service;

import com.example.temp.user.dto.BriefUserProfileDto;

public interface FindBriefUserProfileService {

    BriefUserProfileDto doService(long userId);
}
