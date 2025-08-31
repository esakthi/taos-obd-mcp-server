package com.taos.obd.mcp.dto;

import java.util.List;

public record GetPastLaunchesDetailedQueryResponse(List<Launch> launchesPast) {
}
