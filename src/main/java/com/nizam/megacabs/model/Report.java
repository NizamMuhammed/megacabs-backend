package com.nizam.megacabs.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Report model implementing Data Transfer Object (DTO) pattern
 * This class acts as a container for transferring report data between layers
 * It encapsulates the data structure for various types of reports
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reports")
public class Report {
    @Id
    private String reportId;
    private String reportType;  // BOOKING, REVENUE, DRIVER
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String generatedBy;
    private LocalDateTime generatedAt;
    private Object data;  // Will store the report data as a flexible object
}
