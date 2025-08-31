package com.taos.obd.mcp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Payload(
    String payload_id,
    List<String> customers,
    String nationality,
    String manufacturer,
    String payload_type,
    Float payload_mass_kg,
    String orbit
) {}
