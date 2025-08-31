package com.taos.obd.mcp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LaunchSite(
    String site_id,
    String site_name,
    String site_name_long
) {}
