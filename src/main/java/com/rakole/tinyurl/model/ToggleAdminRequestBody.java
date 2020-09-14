package com.rakole.tinyurl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ToggleAdminRequestBody {
    private int groupId;
    private int userId;
}
