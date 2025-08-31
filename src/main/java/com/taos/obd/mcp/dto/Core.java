package com.taos.obd.mcp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Core(
    String core_serial,
    Integer flight,
    Boolean reused,
    Boolean land_success,
    String landing_type
) {}
