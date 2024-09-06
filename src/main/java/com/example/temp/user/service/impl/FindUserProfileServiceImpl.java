package com.example.temp.user.service.impl;

import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.dto.FindUserProfileCommand;
import com.example.temp.user.dto.UserProfileView;
import com.example.temp.user.service.CheckUserIsBlockedService;
import com.example.temp.user.service.FindUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindUserProfileServiceImpl implements FindUserProfileService {

    private final UserRepository userRepository;
    private final CheckUserIsBlockedService checkUserIsBlockedService;

    @Override
    @Transactional(readOnly = true)
    public UserProfileView doService(FindUserProfileCommand command) {
        checkUserIsBlockedService.doService(command.userId(), command.targetUserId());

        User user = userRepository.findByIdOrElseThrow(command.targetUserId());
        return new UserProfileView(user);
    }
}
