package com.salesianostriana.dam.P01.controller;




import com.salesianostriana.dam.P01.dto.CreatePlaylistDto;
import com.salesianostriana.dam.P01.dto.PlaylistDtoConverter;
import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.model.Playlist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.ArtistRepository;

import com.salesianostriana.dam.P01.repos.PlaylistRepository;
import com.salesianostriana.dam.P01.repos.SongRepository;
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

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final PlaylistDtoConverter playlistDtoConverter;
    private final ArtistRepository artistRepository;

    @GetMapping("/")
    public ResponseEntity<List<Playlist>> findAll(){

        return ResponseEntity.ok().body(playlistRepository.findAll());


    }
  
  @PostMapping("/")
    public ResponseEntity<Playlist> create(@RequestBody Playlist nuevo) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playlistRepository.save(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> edit(@RequestBody CreatePlaylistDto dto){
        if (dto.getName() == null){
            return ResponseEntity.badRequest().build();

        }

       Playlist editada = playlistDtoConverter.createPlaylistDtoToPlaylist(dto);
        Song song = songRepository.findById(dto.getSongId()).orElse(null);

        return ResponseEntity.of(
                playlistRepository.findById(editada.getId()).map(p -> {
                    p.setName(dto.getName());
                    p.setDescription(dto.getDescription());
                    return p;

                })
        );


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        playlistRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> findOne(@PathVariable Long id) {

        return ResponseEntity
                .of(playlistRepository.findById(id));

    }
}
