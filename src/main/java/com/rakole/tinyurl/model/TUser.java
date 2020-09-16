package com.rakole.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TenantUser")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String email;

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "GroupAdmin",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "groupId"))
    private List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Url> urls;
}
 