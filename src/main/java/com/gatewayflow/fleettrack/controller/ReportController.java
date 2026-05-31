package com.gatewayflow.fleettrack.controller;

import com.gatewayflow.fleettrack.report.PdfReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final PdfReportService pdfReportService;

    @GetMapping("/fleet")
    public ResponseEntity<byte[]>
    generateFleetReport() {

        byte[] pdf =
                pdfReportService.generateFleetReport();

        return ResponseEntity.ok()
                .contentType(
                        MediaType.APPLICATION_PDF
                )
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=fleet-report.pdf"
                )
                .body(pdf);
    }
}