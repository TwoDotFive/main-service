package com.example.temp.user.service.impl;

import com.example.temp.baseball.domain.Team;
import com.example.temp.baseball.domain.TeamRepository;
import com.example.temp.baseball.dto.UpdatedUserProfileCommand;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.service.UpdateUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateUserProfileServiceImpl implements UpdateUserProfileService {
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Override
    public void doService(long userId, UpdatedUserProfileCommand command) {
        User user = userRepository.findByIdOrElseThrow(userId);
        Team team = teamRepository.findByIdOrElseThrow(command.favoriteTeam());
        user.updateProfile(command.nickname(), command.oneLiner(), command.profileImageUrl());
        user.updateFavoriteTeam(team);
    }
}
