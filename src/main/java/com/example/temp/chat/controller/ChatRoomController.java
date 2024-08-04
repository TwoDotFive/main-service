package com.example.temp.chat.controller;

import com.example.temp.chat.dto.*;
import com.example.temp.chat.service.CreateChatRoomService;
import com.example.temp.chat.service.FindUserJoinedChatRoomListService;
import com.example.temp.chat.service.LeaveChatRoomService;
import com.example.temp.common.entity.CustomUserDetails;
import com.example.temp.common.util.IdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final LeaveChatRoomService leaveChatRoomService;
    private final CreateChatRoomService createChatRoomService;
    private final FindUserJoinedChatRoomListService findUserJoinedChatRoomListService;

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

    @GetMapping("/list")
    public ResponseEntity<FindUserJoinedChatRoomsResponse> find(@AuthenticationPrincipal CustomUserDetails userDetails) {
        long userId = userDetails.getId();
        List<ChatRoomView> chatRoomViewList = findUserJoinedChatRoomListService.doService(userId);
        FindUserJoinedChatRoomsResponse response = FindUserJoinedChatRoomsResponse.build(userId, chatRoomViewList);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") String roomId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        leaveChatRoomService.doService(userDetails.getId(), IdUtil.toLong(roomId));
        return ResponseEntity.ok().build();
    }
}
