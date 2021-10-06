package com.salesianostriana.dam.P01.controller;

import com.salesianostriana.dam.P01.dto.CreateSongDto;
import com.salesianostriana.dam.P01.dto.SongDtoConverter;
import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.ArtistRepository;
import com.salesianostriana.dam.P01.repos.SongRepository;
import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.ArtistRepository;
import com.salesianostriana.dam.P01.repos.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final SongDtoConverter dtoConverter;

    @PostMapping("/")
    public ResponseEntity<Song> create(@RequestBody CreateSongDto dto){
        if(dto.getArtistId() == null){
            return ResponseEntity.badRequest().build();
        }

        Song nueva = dtoConverter.createSongDtoToSong(dto);
        Artist artist = artistRepository.findById(dto.getArtistId()).orElse(null);

        nueva.setArtist(artist);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(songRepository.save(nueva));
    }

    private final SongRepository repository;

    @GetMapping("/{id}")

    public ResponseEntity<Song> findOne(@PathVariable Long id) {

        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Song>> findAll(){
        return ResponseEntity
                .ok()
                .body(repository.findAll());
    }
}
