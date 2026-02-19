package com.example.Device_Application.service;

import com.example.Device_Application.dto.ExecuteRequest;
import com.example.Device_Application.entity.ExecutionLog;
import com.example.Device_Application.repository.ExecutionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class DeviceService {

    @Autowired
    private final ExecutionLogRepository repository;
    public DeviceService(ExecutionLogRepository repository) {
        this.repository = repository;
    }

    public void execute(Long deviceId, ExecuteRequest request, String username) {

        // 1. Validate timestamp (replay protection)
        if (request.getTimestamp().isBefore(Instant.now().minusSeconds(300))) {
            throw new RuntimeException("Request expired");
        }

        // 2. Check duplicate requestId
        if (repository.existsByRequestId(request.getRequestId())) {
            throw new RuntimeException("Duplicate request");
        }

        // 3. Check nonce replay
        if (repository.existsByNonce(request.getNonce())) {
            throw new RuntimeException("Replay detected");
        }

        // 4. Save log as PENDING
        ExecutionLog log = new ExecutionLog();
        log.setDeviceId(deviceId);
        log.setCommand(request.getCommand());
        log.setRequestId(request.getRequestId());
        log.setNonce(request.getNonce());
        log.setStatus("PENDING");
        log.setTimestamp(Instant.now());
        log.setExecutedBy(username);

        repository.save(log);

        try {
            // 5. Execute device logic (dummy)
            System.out.println("Executing command...");

            log.setStatus("SUCCESS");
            repository.save(log);

        } catch (Exception e) {
            log.setStatus("FAILED");
            repository.save(log);
            throw new RuntimeException("Execution failed");
        }
    }


}
