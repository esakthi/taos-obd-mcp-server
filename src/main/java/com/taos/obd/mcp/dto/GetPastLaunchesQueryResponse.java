package com.taos.obd.mcp.dto;

import java.util.List;

public record GetPastLaunchesQueryResponse(List<Launch> launchesPast) {
}
