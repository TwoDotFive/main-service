package com.example.temp.user.service.impl;

import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.dto.BriefUserProfileDto;
import com.example.temp.user.service.FindBriefUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindBriefUserProfileServiceImpl implements FindBriefUserProfileService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public BriefUserProfileDto doService(long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        return new BriefUserProfileDto(String.valueOf(user.getId()), user.getNickname(), user.getProfileImageUrl());
    }
}
