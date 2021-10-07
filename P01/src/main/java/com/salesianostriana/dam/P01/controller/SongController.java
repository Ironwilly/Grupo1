package com.salesianostriana.dam.P01.controller;

import com.salesianostriana.dam.P01.dto.CreateSongDto;
import com.salesianostriana.dam.P01.dto.PutSongDto;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongRepository repository;
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
                .body(repository.save(nueva));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> findOne(@PathVariable Long id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Song>> findAll(){
        if(repository.findAll().isEmpty()){
            return ResponseEntity
                    .noContent()
                    .build();
        }
        
        return ResponseEntity
                .ok()
                .body(repository.findAll());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> edit(@RequestBody PutSongDto dto, @PathVariable Long id){

        Song edit = dtoConverter.editSongDtoToSong(dto);
        Artist artist = artistRepository.findById(dto.getArtist().getId()).orElse(null);

        edit.setArtist(artist);

        return ResponseEntity.of(
                repository.findById(id).map(s -> {
                    s.setAlbum(dto.getAlbum());
                    s.setYear(dto.getYear());
                    s.setTitle(dto.getTitle());
                    s.setArtist(artist);

                    repository.save(s);
                    return s;
                })
        );
    }
}
