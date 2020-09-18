package com.rakole.tinyurl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TinyUrlsResponseDto {
    private String url;
    private String tinyUrl;
    private int id;
}
