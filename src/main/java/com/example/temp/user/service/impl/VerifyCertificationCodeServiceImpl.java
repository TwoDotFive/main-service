package com.example.temp.user.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.user.service.VerifyCertificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyCertificationCodeServiceImpl implements VerifyCertificationCodeService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void doService(String code, String phoneNumber) {
        if (!hasKey(phoneNumber)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "휴대폰 번호를 다시 확인해 주세요.");
        }

        String savedCode = getSmsCertification(phoneNumber);
        if (!equalsSavedCode(code, savedCode)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "유효 시간이 만료되었거나 인증번호가 일치하지 않습니다.");
        }

        deleteSmsCertification(phoneNumber);
    }

    private String getSmsCertification(String phone) {
        return (String) redisTemplate.opsForValue().get(phone);
    }

    private void deleteSmsCertification(String phone) {
        redisTemplate.delete(phone);
    }

    private boolean equalsSavedCode(String a, String b) {
        return a.equals(b);
    }

    private boolean hasKey(String phone) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(phone));
    }
}
