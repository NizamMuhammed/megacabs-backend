package com.nizam.megacabs.service;

import com.nizam.megacabs.model.Report;
import com.nizam.megacabs.repository.BookingRepository;
import com.nizam.megacabs.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of Template Method and Strategy patterns for report generation
 * - Template Method: Basic structure for report generation is defined with specific implementations
 * - Strategy: Different report generation strategies for different report types
 * - Singleton: Spring manages this as a singleton service bean
 */
@Service
public class ReportService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private DriverRepository driverRepository;

    /**
     * Template method defining the basic algorithm for report generation
     * Each report type follows this template but with different data processing strategies
     */
    private Report generateReport(String reportType, LocalDateTime startDate, LocalDateTime endDate, String userId) {
        Report report = new Report();
        report.setReportType(reportType);
        report.setStartDate(startDate);
        report.setEndDate(endDate);
        report.setGeneratedBy(userId);
        report.setGeneratedAt(LocalDateTime.now());
        return report;
    }

    /**
     * Strategy implementation for booking report generation
     */
    public Report generateBookingReport(LocalDateTime startDate, LocalDateTime endDate, String userId) {
        Report report = generateReport("BOOKING", startDate, endDate, userId);

        // Get bookings within date range
        List<Map<String, Object>> bookingStats = bookingRepository.findAll().stream()
            .filter(b -> {
                LocalDateTime bookingDate = LocalDateTime.parse(b.getDate() + "T" + b.getTime());
                return !bookingDate.isBefore(startDate) && !bookingDate.isAfter(endDate);
            })
            .map(b -> {
                Map<String, Object> stat = new HashMap<>();
                stat.put("bookingId", b.getBookingId());
                stat.put("status", b.getStatus());
                stat.put("price", b.getPrice());
                return stat;
            })
            .collect(Collectors.toList());

        report.setData(bookingStats);
        return report;
    }

    /**
     * Strategy implementation for revenue report generation
     */
    public Report generateRevenueReport(LocalDateTime startDate, LocalDateTime endDate, String userId) {
        Report report = generateReport("REVENUE", startDate, endDate, userId);

        // Calculate revenue statistics
        Map<String, Object> revenueStats = new HashMap<>();
        double totalRevenue = bookingRepository.findAll().stream()
            .filter(b -> {
                LocalDateTime bookingDate = LocalDateTime.parse(b.getDate() + "T" + b.getTime());
                return !bookingDate.isBefore(startDate) && !bookingDate.isAfter(endDate);
            })
            .mapToDouble(b -> b.getPrice())
            .sum();

        revenueStats.put("totalRevenue", totalRevenue);
        report.setData(revenueStats);
        return report;
    }

    /**
     * Strategy implementation for driver report generation
     */
    public Report generateDriverReport(LocalDateTime startDate, LocalDateTime endDate, String userId) {
        Report report = generateReport("DRIVER", startDate, endDate, userId);

        // Get driver statistics
        List<Map<String, Object>> driverStats = driverRepository.findAll().stream()
            .map(d -> {
                Map<String, Object> stat = new HashMap<>();
                stat.put("driverId", d.getDriverId());
                stat.put("driverName", d.getDriverName());
                stat.put("status", d.getDriverStatus());
                return stat;
            })
            .collect(Collectors.toList());

        report.setData(driverStats);
        return report;
    }
}
