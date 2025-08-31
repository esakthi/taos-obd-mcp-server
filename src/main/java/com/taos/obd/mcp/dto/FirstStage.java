package com.taos.obd.mcp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FirstStage(
    List<Core> cores
) {}
