package com.rakole.tinyurl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private int groupId;
    private int cardId;
    private String title;
    private String description;
    private String tinyUrl;
    private String fullUrl;
    private String groupName;
}
