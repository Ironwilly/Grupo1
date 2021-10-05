package com.salesianostriana.dam.P01.controller;

import com.salesianostriana.dam.P01.model.Playlist;
import com.salesianostriana.dam.P01.repos.PlaylistRepository;
import org.aspectj.apache.bcel.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PlaylistController {

    @PostMapping("/")
    public ResponseEntity<Playlist> create(@RequestBody Playlist nuevo) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Repository.save(nuevo));
    }
}
