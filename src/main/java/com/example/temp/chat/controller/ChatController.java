package com.example.temp.chat.controller;

import com.example.temp.chat.dto.*;
import com.example.temp.chat.service.CreateChatroomService;
import com.example.temp.chat.service.FindChatroomListService;
import com.example.temp.chat.service.FindChatroomMessagesService;
import com.example.temp.chat.service.SendChatMessageService;
import com.example.temp.common.dto.IdResponse;
import com.example.temp.common.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final CreateChatroomService createChatroomService;
    private final SendChatMessageService sendChatMessageService;
    private final FindChatroomListService findChatroomListService;
    private final FindChatroomMessagesService findChatroomMessagesService;

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

    @GetMapping("/room")
    public ResponseEntity<List<FindChatroomListResult>> findRoomList(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        List<FindChatroomListResult> response = findChatroomListService.doService(userDetails.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/room/{roomId}/message")
    public ResponseEntity<List<ChatMessageView>> findChatMessages(
            @PathVariable long roomId,
            @RequestParam(name = "lastId", defaultValue = "" + Long.MAX_VALUE) long lastMessageId,
            @RequestParam(name = "size", defaultValue = "30") int pageSize,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        List<ChatMessageView> response = findChatroomMessagesService.doService(roomId, userDetails.getId(), lastMessageId, pageSize);
        return ResponseEntity.ok(response);
    }

}
