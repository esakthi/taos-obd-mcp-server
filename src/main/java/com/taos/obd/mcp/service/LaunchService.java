package com.taos.obd.mcp.service;

import com.taos.obd.mcp.dto.Launch;
import com.taos.obd.mcp.dto.Order;
import com.taos.obd.mcp.tool.LaunchTool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaunchService {

    private final LaunchTool launchTool;

    public LaunchService(LaunchTool launchTool) {
        this.launchTool = launchTool;
    }

    public List<Launch> getPastLaunches(Integer limit) {
        return launchTool.getPastLaunches(limit);
    }

    public List<Launch> getPastLaunchesDetailed(Integer limit, Integer offset) {
        return launchTool.getPastLaunchesDetailed(limit, offset);
    }

    public List<Launch> getUpcomingLaunches(Integer limit) {
        return launchTool.getUpcomingLaunches(limit);
    }

    public Launch getLaunchById(String id) {
        return launchTool.getLaunchById(id);
    }

    public List<Launch> searchLaunchesByMission(String missionName) {
        return launchTool.searchLaunchesByMission(missionName);
    }

    public List<Launch> getAllLaunches(Integer limit, Integer offset, String sort, Order order) {
        return launchTool.getAllLaunches(limit, offset, sort, order);
    }
}
