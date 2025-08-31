package com.taos.obd.mcp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taos.obd.mcp.dto.Launch;
import com.taos.obd.mcp.dto.Order;
import com.taos.obd.mcp.tool.LaunchTool;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LaunchServiceIntegrationTest {

    private static MockWebServer mockWebServer;
    private LaunchService launchService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder(webClient).build();
        LaunchTool launchTool = new LaunchTool(graphQlClient);
        launchService = new LaunchService(launchTool);
    }

    private void enqueueMockResponse(String fieldName, Object launchData) throws JsonProcessingException {
        Map<String, Object> data = new HashMap<>();
        data.put(fieldName, launchData);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", data);

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(responseBody))
                .addHeader("Content-Type", "application/json"));
    }

    @Test
    void getPastLaunches() throws JsonProcessingException {
        enqueueMockResponse("launchesPast", List.of(Map.of("id", "1")));
        List<Launch> launches = launchService.getPastLaunches(1);
        assertEquals(1, launches.size());
    }

    @Test
    void getPastLaunchesDetailed() throws JsonProcessingException {
        enqueueMockResponse("launchesPast", List.of(Map.of("id", "1")));
        List<Launch> launches = launchService.getPastLaunchesDetailed(1, 0);
        assertEquals(1, launches.size());
    }

    @Test
    void getUpcomingLaunches() throws JsonProcessingException {
        enqueueMockResponse("launchesUpcoming", List.of(Map.of("id", "1")));
        List<Launch> launches = launchService.getUpcomingLaunches(1);
        assertEquals(1, launches.size());
    }

    @Test
    void getLaunchById() throws JsonProcessingException {
        enqueueMockResponse("launch", Map.of("id", "1"));
        Launch launch = launchService.getLaunchById("1");
        assertNotNull(launch);
    }

    @Test
    void searchLaunchesByMission() throws JsonProcessingException {
        enqueueMockResponse("launchesByMissionName", List.of(Map.of("id", "1")));
        List<Launch> launches = launchService.searchLaunchesByMission("m");
        assertEquals(1, launches.size());
    }

    @Test
    void getAllLaunches() throws JsonProcessingException {
        enqueueMockResponse("launches", List.of(Map.of("id", "1")));
        List<Launch> launches = launchService.getAllLaunches(1, 0, "s", Order.ASC);
        assertEquals(1, launches.size());
    }
}
