package com.salesianostriana.dam.P01.controller;

import com.salesianostriana.dam.P01.repos.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {

    private final SongRepository repository;

}
