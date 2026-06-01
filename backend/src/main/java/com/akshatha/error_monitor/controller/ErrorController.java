package com.akshatha.error_monitor.controller;

import com.akshatha.error_monitor.model.ErrorLog;
import com.akshatha.error_monitor.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/errors")
@CrossOrigin(origins = "*")
public class ErrorController {

    @Autowired
    private ErrorService errorService;

    @PostMapping
    public ErrorLog saveError(@RequestBody ErrorLog error) {
        return errorService.saveError(error);
    }

    @GetMapping
    public List<ErrorLog> getAllErrors() {
        return errorService.getAllErrors();
    }

    @GetMapping("/{id}")
    public ErrorLog getErrorById(@PathVariable Long id) {
        return errorService.getErrorById(id);
    }

    @PutMapping("/{id}/resolve")
    public ErrorLog resolveError(@PathVariable Long id) {
        return errorService.resolveError(id);
    }
}