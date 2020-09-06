package com.rakole.tinyurl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Url implements Serializable {
    private String longUrl;
    private String shortUrl;
    private int userId;
    private int groupId;
    private boolean is_active;
    private String prefix;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
