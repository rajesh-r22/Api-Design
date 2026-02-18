package com.example.Device_Application.service;

import com.example.Device_Application.repository.ExecutionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private final ExecutionLogRepository repo;
    public DeviceService(ExecutionLogRepository repo) {
        this.repo = repo;
    }

}
