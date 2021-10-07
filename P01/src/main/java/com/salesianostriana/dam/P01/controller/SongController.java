package com.salesianostriana.dam.P01.controller;

import com.salesianostriana.dam.P01.dto.CreateSongDto;
import com.salesianostriana.dam.P01.dto.PutSongDto;
import com.salesianostriana.dam.P01.dto.SongDtoConverter;
import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.ArtistRepository;
import com.salesianostriana.dam.P01.repos.SongRepository;
import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.ArtistRepository;
import com.salesianostriana.dam.P01.repos.SongRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
@Tag(name = "Song", description = "Controlador de canciones")
public class SongController {

    private final SongRepository repository;
    private final ArtistRepository artistRepository;
    private final SongDtoConverter dtoConverter;

    @Operation(summary = "Añade una canción")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha añadido una canción",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido añadir la canción",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @PostMapping("/")
    public ResponseEntity<Song> create(@RequestBody CreateSongDto dto){
        if(dto.getArtistId() == null){
            return ResponseEntity.badRequest().build();
        }

        Song nueva = dtoConverter.createSongDtoToSong(dto);
        Artist artist = artistRepository.findById(dto.getArtistId()).orElse(null);

        nueva.setArtist(artist);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(nueva));
    }




    @Operation(summary = "Optiene la canción elegida por el usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la canción",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido encontrar la canción",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<Song> findOne(@PathVariable Long id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @Operation(summary = "Muestra todas las canciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se muestran todas las canciones",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se a podido todas las canciones",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @GetMapping("/")
    public ResponseEntity<List<Song>> findAll(){
        if(repository.findAll().isEmpty()){
            return ResponseEntity
                    .noContent()
                    .build();
        }

        return ResponseEntity
                .ok()
                .body(repository.findAll());
    }

    @Operation(summary = "Borra a una canción")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "La canción se ha borrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Añade una lista de reproducción para registarla.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "La lista no se a añadido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})

    @PutMapping("/{id}")
    public ResponseEntity<Song> edit(@RequestBody PutSongDto dto, @PathVariable Long id){

        Song edit = dtoConverter.editSongDtoToSong(dto);
        Artist artist = artistRepository.findById(dto.getArtist().getId()).orElse(null);

        edit.setArtist(artist);

        return ResponseEntity.of(
                repository.findById(id).map(s -> {
                    s.setAlbum(dto.getAlbum());
                    s.setYear(dto.getYear());
                    s.setTitle(dto.getTitle());
                    s.setArtist(artist);

                    repository.save(s);
                    return s;
                })
        );
    }
}
