package com.rakole.tinyurl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardUpDateRequestDto {
    private String title;
    private String description;
    private String url;
    private int cardId;
}
