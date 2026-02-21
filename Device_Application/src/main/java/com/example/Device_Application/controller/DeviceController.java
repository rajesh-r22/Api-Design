package com.example.Device_Application.controller;

import com.example.Device_Application.dto.ExecuteRequest;
import com.example.Device_Application.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/{id}/execute")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
    public ResponseEntity<String> execute(
            @PathVariable Long id,
            @RequestBody ExecuteRequest request,
            Authentication authentication) {

        String username = authentication.getName();

        deviceService.execute(id, request, username);

        return ResponseEntity.ok("Execution started");
    }
}
