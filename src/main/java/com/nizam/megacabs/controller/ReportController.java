package com.nizam.megacabs.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nizam.megacabs.model.Report;
import com.nizam.megacabs.service.ReportService;

/**
 * Controller implementing Front Controller and Command patterns
 * - Front Controller: Centralizes request handling for all report types
 * - Command: Each report generation request is handled as a separate command
 * - Facade: Provides a simplified interface to the complex reporting subsystem
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/reports")
@PreAuthorize("hasRole('ADMIN')")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/bookings")
    public ResponseEntity<Report> getBookingReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Authentication authentication) {
        Report report = reportService.generateBookingReport(startDate, endDate, authentication.getName());
        return ResponseEntity.ok(report);
    }

    @GetMapping("/revenue")
    public ResponseEntity<Report> getRevenueReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Authentication authentication) {
        Report report = reportService.generateRevenueReport(startDate, endDate, authentication.getName());
        return ResponseEntity.ok(report);
    }

    @GetMapping("/drivers")
    public ResponseEntity<Report> getDriverReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Authentication authentication) {
        Report report = reportService.generateDriverReport(startDate, endDate, authentication.getName());
        return ResponseEntity.ok(report);
    }
}
