package com.salesianostriana.dam.P01.controller;

import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.repos.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistRepository repository;

    @PostMapping("/")
    public ResponseEntity<Artist> create(@RequestBody Artist nuevo) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(nuevo));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> edit(@RequestBody Artist artist, @PathVariable Long id){
        return ResponseEntity.of(
            repository.findById(id).map(a -> {
                a.setName(artist.getName());
                repository.save(a);
                return a;
            })
        );
    }
}
