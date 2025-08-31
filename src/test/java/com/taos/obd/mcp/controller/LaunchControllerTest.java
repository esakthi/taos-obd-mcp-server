package com.taos.obd.mcp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taos.obd.mcp.dto.Launch;
import com.taos.obd.mcp.dto.Order;
import com.taos.obd.mcp.service.LaunchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LaunchController.class)
class LaunchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LaunchService launchService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getPastLaunches_shouldReturnLaunches() throws Exception {
        List<Launch> launches = Collections.singletonList(new Launch("1", "Mission 1", null, null, null, null, null, null, null, null, null));
        when(launchService.getPastLaunches(10)).thenReturn(launches);

        mockMvc.perform(get("/api/v1/launches/past"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].mission_name").value("Mission 1"));
    }

    @Test
    void getPastLaunchesDetailed_shouldReturnDetailedLaunches() throws Exception {
        List<Launch> launches = Collections.singletonList(new Launch("1", "Mission 1", null, null, null, true, "Details", false, null, null, null));
        when(launchService.getPastLaunchesDetailed(10, 0)).thenReturn(launches);

        mockMvc.perform(get("/api/v1/launches/past/detailed"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].details").value("Details"));
    }

    @Test
    void getUpcomingLaunches_shouldReturnUpcomingLaunches() throws Exception {
        List<Launch> launches = Collections.singletonList(new Launch("2", "Upcoming Mission", null, null, null, null, null, true, null, null, null));
        when(launchService.getUpcomingLaunches(10)).thenReturn(launches);

        mockMvc.perform(get("/api/v1/launches/upcoming"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].upcoming").value(true));
    }

    @Test
    void getLaunchById_shouldReturnLaunch() throws Exception {
        Launch launch = new Launch("123", "Specific Mission", null, null, null, null, null, false, null, null, null);
        when(launchService.getLaunchById("123")).thenReturn(launch);

        mockMvc.perform(get("/api/v1/launches/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"));
    }

    @Test
    void getLaunchById_whenServiceThrowsException_shouldReturnInternalServerError() throws Exception {
        when(launchService.getLaunchById("123")).thenThrow(new RuntimeException("Service Error"));

        mockMvc.perform(get("/api/v1/launches/123"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Service Error"));
    }

    @Test
    void searchLaunchesByMission_shouldReturnMatchingLaunches() throws Exception {
        List<Launch> launches = Collections.singletonList(new Launch("1", "Search Mission", null, null, null, null, null, false, null, null, null));
        when(launchService.searchLaunchesByMission("Search")).thenReturn(launches);

        mockMvc.perform(get("/api/v1/launches/search").param("missionName", "Search"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].mission_name").value("Search Mission"));
    }

    @Test
    void getAllLaunches_shouldReturnAllLaunches() throws Exception {
        List<Launch> launches = Collections.singletonList(new Launch("1", "Any Mission", null, null, null, null, null, false, null, null, null));
        when(launchService.getAllLaunches(10, 0, null, Order.ASC)).thenReturn(launches);

        mockMvc.perform(get("/api/v1/launches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].mission_name").value("Any Mission"));
    }
}
