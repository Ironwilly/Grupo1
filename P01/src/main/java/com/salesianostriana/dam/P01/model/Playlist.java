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
    private String descripcion;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Song> listSongs;





}
