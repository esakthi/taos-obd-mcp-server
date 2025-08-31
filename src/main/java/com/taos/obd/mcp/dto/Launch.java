package com.taos.obd.mcp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Launch(
    String id,
    String mission_name,
    String launch_date_local,
    String launch_date_utc,
    String launch_year,
    Boolean launch_success,
    String details,
    Boolean upcoming,
    LaunchSite launch_site,
    Links links,
    Rocket rocket
) {}
