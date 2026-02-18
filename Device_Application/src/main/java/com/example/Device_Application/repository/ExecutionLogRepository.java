package com.example.Device_Application.repository;

import com.example.Device_Application.entity.ExecutionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionLogRepository extends JpaRepository<ExecutionLog, Long> {
    boolean existsByRequestId(String requestId);

    boolean existsByNonce(String nonce);
}
