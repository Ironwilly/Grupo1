package com.salesianostriana.dam.P01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor

public class SongController {

    @PutMapping("/{id}")
    public ResponseEntity<Song> edit (
           Request
    )
}
