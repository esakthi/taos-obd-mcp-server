package com.taos.obd.mcp.dto;

import java.util.List;

public record GetAllLaunchesQueryResponse(List<Launch> launches) {
}
