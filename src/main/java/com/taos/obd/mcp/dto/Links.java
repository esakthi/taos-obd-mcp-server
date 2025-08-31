package com.taos.obd.mcp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Links(
    String mission_patch,
    String mission_patch_small,
    String article_link,
    String video_link,
    String wikipedia,
    String youtube_id,
    List<String> flickr_images
) {}
