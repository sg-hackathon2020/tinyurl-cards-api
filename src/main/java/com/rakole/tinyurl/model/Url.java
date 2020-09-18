package com.rakole.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Url implements Serializable {
    private String url;
    private String hash;
    private boolean isActive;
    private String prefix;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy = "url")
    @JsonBackReference
    private Card card;
    private LocalDate date;

    @ManyToOne
    @JsonBackReference(value = "tuser_url")
    @JoinColumn(name = "userId")
    private TUser user;
}