package com.salesianostriana.dam.P01.repos;

import com.salesianostriana.dam.P01.model.Playlist;
import com.salesianostriana.dam.P01.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository
                extends JpaRepository<Playlist,Long> {
}