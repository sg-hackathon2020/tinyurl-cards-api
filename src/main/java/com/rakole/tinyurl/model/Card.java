package com.rakole.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "urlId", nullable = false)
    private Url url;
    @JsonBackReference(value = "group-card")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Group group;
}
