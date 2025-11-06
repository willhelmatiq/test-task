package com.example.test_task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class Controller {

    private final MyService service;

    @PostMapping("/process-file")
    public ResponseEntity<String> processFile(@RequestBody FileRequest request) {
        try {
            int result = service.processExcel(request.getFilePath(), request.getN());
            return ResponseEntity.ok(String.valueOf(result));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }


}
