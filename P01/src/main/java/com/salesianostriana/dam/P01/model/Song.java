package com.salesianostriana.dam.P01.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Song {

    @Id
    @GeneratedValue
    private Long id;

    private String tittle;

    private Artist artist;

    private String album;

    private String year;
}
