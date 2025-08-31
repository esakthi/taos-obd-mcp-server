package com.taos.obd.mcp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Rocket(
    String rocket_id,
    String rocket_name,
    String rocket_type,
    FirstStage first_stage,
    SecondStage second_stage
) {}
