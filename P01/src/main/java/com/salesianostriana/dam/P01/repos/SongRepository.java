package com.salesianostriana.dam.P01.repos;

import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository
        extends JpaRepository<Song,Long> {
}
