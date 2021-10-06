package com.salesianostriana.dam.P01.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    @Column
    @ElementCollection
    private List<Song> listSongs;


    public Playlist(String name, String description) {
        this.name = name;
        this.description = description;

    }
}
