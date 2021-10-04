package com.salesianostriana.dam.P01.repos;

import com.salesianostriana.dam.P01.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
}
