package com.taos.obd.mcp.dto;

import java.util.List;

public record SearchLaunchesByMissionQueryResponse(List<Launch> launchesByMissionName) {
}
