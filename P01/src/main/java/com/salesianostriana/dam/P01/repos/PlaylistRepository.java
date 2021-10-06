package com.salesianostriana.dam.P01.repos;

import com.salesianostriana.dam.P01.model.Playlist;

import org.springframework.data.jpa.repository.JpaRepository;
import com.salesianostriana.dam.P01.model.Song;

public interface PlaylistRepository extends JpaRepository<Playlist,Long> {

}
