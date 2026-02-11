package com.rustambek.clinic.dashboard.controller;

import com.rustambek.clinic.dashboard.dto.DashboardDto;
import com.rustambek.clinic.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/widget")
    public DashboardDto get(
            @RequestParam(required = true) String fromDate,
            @RequestParam(required = true) String toDate
    ) {
        return dashboardService.getWidget(fromDate,toDate);
    }
}
