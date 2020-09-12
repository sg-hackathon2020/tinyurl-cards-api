package com.rakole.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "org_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String groupName;
    private String clusterName;
    private String tribeName;
    private String ftName;

/*
    @JsonManagedReference
*/
    @OneToMany(mappedBy = "group")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Card> cards;
}