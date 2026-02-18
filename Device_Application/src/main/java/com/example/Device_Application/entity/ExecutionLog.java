package com.example.Device_Application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "execution_log",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "requestId"),
                @UniqueConstraint(columnNames = "nonce")
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long deviceId;

    private String command;

    private String requestId;

    private String nonce;

    private String status; // PENDING, SUCCESS, FAILED

    private Instant timestamp;

    private String executedBy;
}
