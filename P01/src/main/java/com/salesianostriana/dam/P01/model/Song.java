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

    private String tittle;

    
    @ManyToOne
    private Artist artist;

    private String album;

    private String year;
}
