package com.example.temp.common.file;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/file/presinged-url")
    public ResponseEntity<String> getPresignedUrl() {
        return ResponseEntity.ok(fileService.generatePresignedUrl());
    }
}
