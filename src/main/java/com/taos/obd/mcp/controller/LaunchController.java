package com.taos.obd.mcp.controller;

import com.taos.obd.mcp.dto.Launch;
import com.taos.obd.mcp.dto.Order;
import com.taos.obd.mcp.service.LaunchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/launches")
@Tag(name = "SpaceX Launches", description = "API for fetching SpaceX launch data")
public class LaunchController {

    private final LaunchService launchService;

    public LaunchController(LaunchService launchService) {
        this.launchService = launchService;
    }

    @GetMapping("/past")
    @Operation(summary = "Get past launches", description = "Get a list of past SpaceX launches with an optional limit.")
    public ResponseEntity<List<Launch>> getPastLaunches(
            @Parameter(description = "Number of launches to return") @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(launchService.getPastLaunches(limit));
    }

    @GetMapping("/past/detailed")
    @Operation(summary = "Get past launches with detailed information", description = "Get a detailed list of past SpaceX launches with pagination.")
    public ResponseEntity<List<Launch>> getPastLaunchesDetailed(
            @Parameter(description = "Number of launches to return") @RequestParam(required = false, defaultValue = "10") Integer limit,
            @Parameter(description = "Offset for pagination") @RequestParam(required = false, defaultValue = "0") Integer offset) {
        return ResponseEntity.ok(launchService.getPastLaunchesDetailed(limit, offset));
    }

    @GetMapping("/upcoming")
    @Operation(summary = "Get upcoming launches", description = "Get a list of upcoming SpaceX launches with an optional limit.")
    public ResponseEntity<List<Launch>> getUpcomingLaunches(
            @Parameter(description = "Number of launches to return") @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(launchService.getUpcomingLaunches(limit));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific launch by ID", description = "Get detailed information about a specific launch by its ID.")
    public ResponseEntity<Launch> getLaunchById(
            @Parameter(description = "ID of the launch") @PathVariable String id) {
        return ResponseEntity.ok(launchService.getLaunchById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search launches by mission name", description = "Search for launches that match a given mission name.")
    public ResponseEntity<List<Launch>> searchLaunchesByMission(
            @Parameter(description = "Mission name to search for") @RequestParam String missionName) {
        return ResponseEntity.ok(launchService.searchLaunchesByMission(missionName));
    }

    @GetMapping
    @Operation(summary = "Get all launches", description = "Get a list of all launches with pagination and sorting options.")
    public ResponseEntity<List<Launch>> getAllLaunches(
            @Parameter(description = "Number of launches to return") @RequestParam(required = false, defaultValue = "10") Integer limit,
            @Parameter(description = "Offset for pagination") @RequestParam(required = false, defaultValue = "0") Integer offset,
            @Parameter(description = "Field to sort by (e.g., 'mission_name')") @RequestParam(required = false) String sort,
            @Parameter(description = "Sort order ('ASC' or 'DESC')") @RequestParam(required = false, defaultValue = "ASC") Order order) {
        return ResponseEntity.ok(launchService.getAllLaunches(limit, offset, sort, order));
    }
}
