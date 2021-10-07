package com.salesianostriana.dam.P01.controller;

import com.salesianostriana.dam.P01.dto.*;
import com.salesianostriana.dam.P01.model.Playlist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.dto.CreatePlaylistDto;
import com.salesianostriana.dam.P01.dto.PlaylistDtoConverter;
import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.ArtistRepository;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.PlaylistRepository;
import com.salesianostriana.dam.P01.repos.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final PlaylistDtoConverter playlistDtoConverter;
    private final PlaylistRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Playlist>> findAll(){

        return ResponseEntity.ok().body(playlistRepository.findAll());
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<Playlist> findPlaylistSongs(@PathVariable Long id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Playlist> create(@RequestBody CreatePlaylistDto dto) {
        if(dto.getSongId() == null){
            return ResponseEntity.badRequest().build();
        }

        Playlist nuevaPlaylist = playlistDtoConverter.createPlaylistDtoToPlaylist(dto);
        Song nuevaSong = songRepository.findById(dto.getSongId()).orElse(null);

        nuevaPlaylist.setSongs(List.of(nuevaSong));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playlistRepository.save(nuevaPlaylist));
    }

    @PostMapping("/{id1}/songs/{id2}")
    public ResponseEntity<Playlist> addSong(@RequestBody Playlist playlist,
                                            @PathVariable Long id1,
                                            @PathVariable Long id2){
        Optional <Playlist> playlistActual = playlistRepository.findById(id1);
        Optional <Song> nuevaSong = songRepository.findById(id2);

        playlistActual.get().getSongs().add(nuevaSong.get());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playlistRepository.save(playlistActual.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> edit(@RequestBody CreatePlaylistDto dto, @PathVariable Long id){
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

    @DeleteMapping("/{id1}/songs/{id2}")
    public ResponseEntity<?> deleteSongFromPlaylist(@RequestBody Playlist playlist,
                                                    @PathVariable Long id1,
                                                    @PathVariable Long id2){
        Optional <Playlist> playlistActual = playlistRepository.findById(id1);

        playlistActual.get().getSongs().remove(songRepository.findById(id2).get());

        playlistRepository.save(playlistActual.get());

        return ResponseEntity.noContent().build();
    }




    @GetMapping("/{id}")
    public ResponseEntity<?> GetPlaylistDto(@RequestBody Playlist playlist,
                                                    @PathVariable Long id ){

        Optional <Playlist> playlistActual = playlistRepository.findById(id);

        playlistRepository.save(playlistActual.get());

        return ResponseEntity.ok().body(playlistActual.get());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Playlist> findOne(@PathVariable Long id) {

        return ResponseEntity
                .of(playlistRepository.findById(id));

    }
}
