package com.taos.obd.mcp.dto;

import java.util.List;

public record GetUpcomingLaunchesQueryResponse(List<Launch> launchesUpcoming) {
}
