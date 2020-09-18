package com.rakole.tinyurl.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAdminView {
    private String email;
    private int id;
    @EqualsAndHashCode.Exclude
    private boolean isAdmin;
    private int groupId;
}
