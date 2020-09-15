package com.rakole.tinyurl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardCrudDto {
    private String title;
    private String description;
    private String url;
    private Integer groupId;
    private String prefix;
}
