package com.salesianostriana.dam.P01.controller;


import com.salesianostriana.dam.P01.model.Playlist;
import com.salesianostriana.dam.P01.repos.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository repository;

    @GetMapping("/lists")
    public ResponseEntity<List<Playlist>> findAll(){

        return ResponseEntity.ok().body(repository.findAll());


    }


}
