package com.salesianostriana.dam.P01.controller;

import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {

    private final SongRepository repository;

    @GetMapping("/{id}")

    public ResponseEntity<Song> findOne(@PathVariable Long id) {

        return ResponseEntity.of(repository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> edit(@RequestBody Song song, @PathVariable Long id){
        return ResponseEntity.of(
                repository.findById(id).map(s -> {
                    s.setArtist(song.getArtist());
                    s.setAlbum(song.getAlbum());
                    s.setYear(song.getYear());
                    return s;
                })
        );
    }

    

}
