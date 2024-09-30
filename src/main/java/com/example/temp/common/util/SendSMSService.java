package com.example.temp.common.util;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.model.MessageType;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendSMSService {
    private final DefaultMessageService messageService;
    private final String apiKey;
    private final String apiSecret;
    private final String fromNumber;
    private final String domain;

    public SendSMSService(@Value("${coolsms.api-key}") String apiKey,
                          @Value("${coolsms.api-secret}") String apiSecret,
                          @Value("${coolsms.from-number}") String fromNumber,
                          @Value("${coolsms.domain}") String domain) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.fromNumber = fromNumber;
        this.domain = domain;
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, domain);
    }

    // 단일 인증번호 전송하기
    public void sendSMS(String toNumber, String content) {
        Message message = new Message();

        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom(fromNumber);
        message.setTo(toNumber);
        message.setText(content);
        message.setType(MessageType.SMS);

        messageService.sendOne(new SingleMessageSendingRequest(message));
    }

}
