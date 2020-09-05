package com.rakole.tinyurl.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Url implements Serializable {
    String longUrl;
    String shortUrl;
    int userId;
    int groupId;
    boolean is_active;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


}
