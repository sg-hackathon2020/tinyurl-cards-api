package com.rakole.tinyurl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardResponseEntityDto {
    private int id;
    private String title;
    private String description;
    private String longUrl;
    private String shortUrl;
}
