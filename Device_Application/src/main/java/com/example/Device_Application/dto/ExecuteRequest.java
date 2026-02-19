package com.example.Device_Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteRequest {
    private String command;
    private String requestId;
    private String nonce;
    private Instant timestamp;
}
