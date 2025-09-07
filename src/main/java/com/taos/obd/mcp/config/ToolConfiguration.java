package com.taos.obd.mcp.config;

import com.taos.obd.mcp.tool.LaunchTool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class ToolConfiguration {

    @Bean(name = "getPastLaunches")
    @Description("Get a list of past SpaceX launches with an optional limit.")
    public Function<Integer, Object> getPastLaunches(LaunchTool launchTool) {
        return limit -> launchTool.getPastLaunches(limit);
    }

    @Bean(name = "getPastLaunchesDetailed")
    @Description("Get a detailed list of past SpaceX launches with pagination.")
    public Function<LaunchTool.Request, Object> getPastLaunchesDetailed(LaunchTool launchTool) {
        return request -> launchTool.getPastLaunchesDetailed(request.limit(), request.offset());
    }

    @Bean(name = "getUpcomingLaunches")
    @Description("Get a list of upcoming SpaceX launches with an optional limit.")
    public Function<Integer, Object> getUpcomingLaunches(LaunchTool launchTool) {
        return limit -> launchTool.getUpcomingLaunches(limit);
    }

    @Bean(name = "getLaunchById")
    @Description("Get detailed information about a specific launch by its ID.")
    public Function<String, Object> getLaunchById(LaunchTool launchTool) {
        return id -> launchTool.getLaunchById(id);
    }

    @Bean(name = "searchLaunchesByMission")
    @Description("Search for launches that match a given mission name.")
    public Function<String, Object> searchLaunchesByMission(LaunchTool launchTool) {
        return missionName -> launchTool.searchLaunchesByMission(missionName);
    }

    @Bean(name = "getAllLaunches")
    @Description("Get a list of all launches with pagination and sorting options.")
    public Function<LaunchTool.Request, Object> getAllLaunches(LaunchTool launchTool) {
        return request -> launchTool.getAllLaunches(request.limit(), request.offset(), request.sort(), request.order());
    }
}
