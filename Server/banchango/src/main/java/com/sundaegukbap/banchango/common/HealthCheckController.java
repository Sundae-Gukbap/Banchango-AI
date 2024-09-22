package com.sundaegukbap.banchango.common;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Tag(name = "health check 컨트롤러")
public class HealthCheckController {
    @GetMapping
    public ResponseEntity healthCheck() {
        return ResponseEntity.ok("OK");
    }
}
