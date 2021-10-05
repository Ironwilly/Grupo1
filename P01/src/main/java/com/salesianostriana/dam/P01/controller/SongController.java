package com.salesianostriana.dam.P01.controller;

import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongRepository repository;

    @PostMapping("/")
    public ResponseEntity<Song> create(@RequestBody Song nueva){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
