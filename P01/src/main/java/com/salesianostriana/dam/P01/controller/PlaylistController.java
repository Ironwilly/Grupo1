package com.salesianostriana.dam.P01.controller;



import com.salesianostriana.dam.P01.dto.CreatePlaylistDto;
import com.salesianostriana.dam.P01.dto.PlaylistDtoConverter;
import com.salesianostriana.dam.P01.model.Playlist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.PlaylistRepository;
import com.salesianostriana.dam.P01.repos.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final PlaylistDtoConverter converter;

    @GetMapping("/")
    public ResponseEntity<List<Playlist>> findAll(){

        return ResponseEntity.ok().body(playlistRepository.findAll());
    }
  
    @PostMapping("/")
    public ResponseEntity<Playlist> create(@RequestBody CreatePlaylistDto dto) {
        if(dto.getSongId() == null){
            return ResponseEntity.badRequest().build();
        }

    }


}
