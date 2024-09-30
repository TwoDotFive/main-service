package com.example.temp.user.service.impl;

import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import com.example.temp.user.service.DeleteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteUserServiceImpl implements DeleteUserService {
    private final UserRepository userRepository;

    @Override
    public void doService(long id) {
        User user = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(user);
    }
}
