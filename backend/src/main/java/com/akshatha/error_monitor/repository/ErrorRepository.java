package com.akshatha.error_monitor.repository;

import com.akshatha.error_monitor.model.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends JpaRepository<ErrorLog, Long> {

}