package com.salesianostriana.dam.P01.controller;



import com.salesianostriana.dam.P01.model.Playlist;
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
  
  @PostMapping("/")
    public ResponseEntity<Playlist> create(@RequestBody Playlist nuevo) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> edit(@RequestBody Playlist playlist,@PathVariable Long id){

        return  ResponseEntity.of(
                repository.findById(id).map(p -> {
                    p.setName(playlist.getName());
                    p.setDescripcion((playlist.getDescripcion()));
                    return p;
                })
        );
    }


}
