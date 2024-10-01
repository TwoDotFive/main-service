package com.example.temp.chat.controller;

import com.example.temp.chat.dto.CreateChatroomCommand;
import com.example.temp.chat.dto.CreateChatroomRequest;
import com.example.temp.chat.dto.SendChatMessageRequest;
import com.example.temp.chat.service.CreateChatroomService;
import com.example.temp.chat.service.SendChatMessageService;
import com.example.temp.common.dto.IdResponse;
import com.example.temp.common.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final CreateChatroomService createChatroomService;
    private final SendChatMessageService sendChatMessageService;

    @PostMapping("/room")
    public ResponseEntity<IdResponse> createRoom(
            @RequestBody CreateChatroomRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        CreateChatroomCommand command = request.buildCommand(userDetails.getId());
        Long chatroomId = createChatroomService.doService(command);
        return ResponseEntity.ok(IdResponse.build(chatroomId));
    }

    @PostMapping("/message")
    public ResponseEntity<Void> createRoom(
            @RequestBody SendChatMessageRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        sendChatMessageService.doService(userDetails.getId(), Long.parseLong(request.roomId()), request.message());
        return ResponseEntity.ok().build();
    }
}
