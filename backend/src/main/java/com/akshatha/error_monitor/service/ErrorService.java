package com.akshatha.error_monitor.service;

import com.akshatha.error_monitor.model.ErrorLog;
import com.akshatha.error_monitor.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ErrorService {

    @Autowired
    private ErrorRepository errorRepository;

    @Autowired
    private AIService aiService;

    public ErrorLog saveError(ErrorLog error) {
        error.setTimestamp(LocalDateTime.now());
        error.setStatus("OPEN");
        
        // Get AI fix suggestion
        String suggestion = aiService.getFixSuggestion(error.getMessage());
        error.setAiSuggestion(suggestion);
        
        return errorRepository.save(error);
    }

    public List<ErrorLog> getAllErrors() {
        return errorRepository.findAll();
    }

    public ErrorLog getErrorById(Long id) {
        return errorRepository.findById(id).orElse(null);
    }

    public ErrorLog resolveError(Long id) {
        ErrorLog error = errorRepository.findById(id).orElse(null);
        if (error != null) {
            error.setStatus("RESOLVED");
            return errorRepository.save(error);
        }
        return null;
    }
}