package com.example.temp.chat.controller;

import com.example.temp.chat.dto.CreateChatRoomCommand;
import com.example.temp.chat.dto.CreateChatRoomRequest;
import com.example.temp.chat.dto.CreateChatRoomResponse;
import com.example.temp.chat.service.CreateChatRoomService;
import com.example.temp.common.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final CreateChatRoomService createChatRoomService;

    @PostMapping
    public ResponseEntity<CreateChatRoomResponse> create(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody CreateChatRoomRequest request
    ) {
        CreateChatRoomCommand command = request.toCommand(userDetails.getId());
        Long chatRoomId = createChatRoomService.doService(command);
        CreateChatRoomResponse response = CreateChatRoomResponse.build(chatRoomId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
