package com.salesianostriana.dam.P01.controller;



import com.salesianostriana.dam.P01.dto.GetPlaylistDto;
import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.model.Playlist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Playlist>> findAll(){

        return ResponseEntity.ok().body(repository.findAll());


    }
  
  @PostMapping("/")
    public ResponseEntity<Playlist> create(@RequestBody Playlist nuevo) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(nuevo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> findOne(@PathVariable Long id) {

        return ResponseEntity
                .of(repository.findById(id));

    }
}
