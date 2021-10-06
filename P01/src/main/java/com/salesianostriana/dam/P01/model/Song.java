package com.salesianostriana.dam.P01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne
    private Artist artist;

    private String album;
    private String year;
    private Long artistId;

    public Song(String title, String album, String year, Long artistId) {
        this.title = title;
        this.album = album;
        this.year = year;
        this.artistId = artistId;
    }
}
