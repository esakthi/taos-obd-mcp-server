package com.taos.obd.mcp.service;

import com.taos.obd.mcp.dto.Launch;
import com.taos.obd.mcp.dto.Order;
import com.taos.obd.mcp.tool.LaunchTool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LaunchServiceTest {

    @Mock
    private LaunchTool launchTool;

    @InjectMocks
    private LaunchService launchService;

    @Test
    void getPastLaunches_shouldReturnLaunches() {
        // Given
        List<Launch> expectedLaunches = Collections.singletonList(new Launch("1", "Mission 1", null, null, null, null, null, null, null, null, null));
        when(launchTool.getPastLaunches(1)).thenReturn(expectedLaunches);

        // When
        List<Launch> actualLaunches = launchService.getPastLaunches(1);

        // Then
        assertEquals(expectedLaunches, actualLaunches);
        verify(launchTool).getPastLaunches(1);
    }

    @Test
    void getPastLaunches_whenToolThrowsException_shouldPropagateException() {
        // Given
        when(launchTool.getPastLaunches(1)).thenThrow(new RuntimeException("Tool Error"));

        // When & Then
        assertThrows(RuntimeException.class, () -> launchService.getPastLaunches(1));
        verify(launchTool).getPastLaunches(1);
    }

    @Test
    void getPastLaunchesDetailed_shouldReturnLaunches() {
        List<Launch> expectedLaunches = Collections.singletonList(new Launch("1", "Mission 1", null, null, null, null, null, null, null, null, null));
        when(launchTool.getPastLaunchesDetailed(1, 0)).thenReturn(expectedLaunches);
        List<Launch> actualLaunches = launchService.getPastLaunchesDetailed(1, 0);
        assertEquals(expectedLaunches, actualLaunches);
        verify(launchTool).getPastLaunchesDetailed(1, 0);
    }

    @Test
    void getUpcomingLaunches_shouldReturnLaunches() {
        List<Launch> expectedLaunches = Collections.singletonList(new Launch("1", "Mission 1", null, null, null, null, null, null, null, null, null));
        when(launchTool.getUpcomingLaunches(1)).thenReturn(expectedLaunches);
        List<Launch> actualLaunches = launchService.getUpcomingLaunches(1);
        assertEquals(expectedLaunches, actualLaunches);
        verify(launchTool).getUpcomingLaunches(1);
    }

    @Test
    void getLaunchById_shouldReturnLaunch() {
        Launch expectedLaunch = new Launch("1", "Mission 1", null, null, null, null, null, null, null, null, null);
        when(launchTool.getLaunchById("1")).thenReturn(expectedLaunch);
        Launch actualLaunch = launchService.getLaunchById("1");
        assertEquals(expectedLaunch, actualLaunch);
        verify(launchTool).getLaunchById("1");
    }

    @Test
    void searchLaunchesByMission_shouldReturnLaunches() {
        List<Launch> expectedLaunches = Collections.singletonList(new Launch("1", "Mission 1", null, null, null, null, null, null, null, null, null));
        when(launchTool.searchLaunchesByMission("Mission 1")).thenReturn(expectedLaunches);
        List<Launch> actualLaunches = launchService.searchLaunchesByMission("Mission 1");
        assertEquals(expectedLaunches, actualLaunches);
        verify(launchTool).searchLaunchesByMission("Mission 1");
    }

    @Test
    void getAllLaunches_shouldReturnLaunches() {
        List<Launch> expectedLaunches = Collections.singletonList(new Launch("1", "Mission 1", null, null, null, null, null, null, null, null, null));
        when(launchTool.getAllLaunches(10, 0, "mission_name", Order.ASC)).thenReturn(expectedLaunches);
        List<Launch> actualLaunches = launchService.getAllLaunches(10, 0, "mission_name", Order.ASC);
        assertEquals(expectedLaunches, actualLaunches);
        verify(launchTool).getAllLaunches(10, 0, "mission_name", Order.ASC);
    }
}
