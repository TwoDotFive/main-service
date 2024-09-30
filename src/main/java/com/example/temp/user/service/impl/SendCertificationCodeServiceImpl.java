package com.example.temp.user.service.impl;

import com.example.temp.common.util.SendSMSService;
import com.example.temp.user.service.SendCertificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@EnableRedisRepositories
public class SendCertificationCodeServiceImpl implements SendCertificationCodeService {
    private static final long CERTIFICATION_CODE_EXPIRE_TIME = 3 * 60 * 1000L; // 3분
    private final SendSMSService sendSMSService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void doService(String toNumber) {
        String certificationCode = createRandomNumber();
        String content = "[팬풀] 인증 번호 [" + certificationCode + "] 입니다";

        // Redis에 휴대폰 번호와 인증코드 저장
        saveCertificationCodeToRedis(toNumber, certificationCode);

        sendSMSService.sendSMS(toNumber, content);
    }

    private String createRandomNumber() {
        String randomNum = "";
        for (int i = 0; i < 6; i++) {
            randomNum += Integer.toString((int) (Math.random() * 10));
        }
        return randomNum;
    }

    private void saveCertificationCodeToRedis(String phoneNumber, String certificationCode) {
        redisTemplate.opsForValue().set(phoneNumber, certificationCode, CERTIFICATION_CODE_EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }
}
