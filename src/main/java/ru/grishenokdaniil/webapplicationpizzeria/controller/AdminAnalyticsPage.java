package ru.grishenokdaniil.webapplicationpizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grishenokdaniil.webapplicationpizzeria.service.ReportService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AdminAnalyticsPage {
    private final ReportService reportService;
    @GetMapping("/admin/stats")
    public String stat(){
        return "AdminStats";
    }
    @PostMapping("/admin/stats/generateOrderReport")
    public ResponseEntity<byte[]> generateOrderReport() throws IOException {
        byte[] pdfReport = reportService.generateOrderReport();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order_report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfReport);
    }
}
