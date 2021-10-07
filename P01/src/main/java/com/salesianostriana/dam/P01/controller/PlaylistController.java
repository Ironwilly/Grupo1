package com.salesianostriana.dam.P01.controller;



import com.salesianostriana.dam.P01.model.Playlist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Playlist>> findAll(){

        return ResponseEntity.ok().body(repository.findAll());


    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<Playlist> findPlaylistSongs(@PathVariable Long id) {
        return ResponseEntity.of(repository.findById(id));
    }
  
  @PostMapping("/")
    public ResponseEntity<Playlist> create(@RequestBody Playlist nuevo) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(nuevo));
    }


}
