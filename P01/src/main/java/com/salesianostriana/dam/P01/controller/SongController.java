package com.salesianostriana.dam.P01.controller;

import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.ArtistRepository;
import com.salesianostriana.dam.P01.repos.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {

    private final SongRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Song>> findAll(){
        return ResponseEntity
                .ok()
                .body(repository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
