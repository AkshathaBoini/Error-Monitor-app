package com.akshatha.error_monitor.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
@Data
@Entity
@Table(name = "error_logs")
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String severity;

    private String status;

    @Column(columnDefinition = "TEXT")
private String aiSuggestion;

    private LocalDateTime timestamp;
}