package com.salesianostriana.dam.P01.controller;

import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.repos.ArtistRepository;
import com.salesianostriana.dam.P01.repos.SongRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
@Tag(name = "Artista", description = "Controlador de Artistas")
public class ArtistController {

    private final ArtistRepository repository;
    private final SongRepository songRepository;

    @Operation(summary = "Obtiene una lista de todos los artistas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Se ha encontrado la lista de artistas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Artist.class))}),
        @ApiResponse(responseCode = "400",
            description = "No se ha encontrado ningún artista",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Artist.class))})})
    @GetMapping("/")
    public ResponseEntity<List<Artist>> findAll(){
        if(repository.findAll().isEmpty()){
            return ResponseEntity
                    .noContent()
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(repository.findAll());
    }

    @Operation(summary = "Crea un nuevo artista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el artista",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear el artista",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @PostMapping("/")
    public ResponseEntity<Artist> create(@RequestBody Artist nuevo) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(nuevo));
    }

    @Operation(summary = "Edita los atributos de un artista previamente creado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el artista y se ha modificado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado al artista indicado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @PutMapping("/{id}")
    public ResponseEntity<Artist> edit(@RequestBody Artist artist,
                                       @Parameter(description = "ID del artista a buscar")
                                       @PathVariable Long id){
        return ResponseEntity.of(
            repository.findById(id).map(a -> {
                a.setName(artist.getName());
                repository.save(a);
                return a;
            })
        );
    }

    @Operation(summary = "Obtiene un artista por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el artista indicado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado al artista indicado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<Artist> findOne (@Parameter(description = "ID del artista a buscar")
                                               @PathVariable Long id) {
        return ResponseEntity
                .of(repository.findById(id));
    }


    @Operation(summary = "Borra a un artista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "El artista se ha borrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Parameter(description = "ID del artista a borrar")
                                        @PathVariable Long id) {
        // 1) Buscar todas las canciones del artista.
        // 2) Settear a null el artista de estas canciones
        // 3) Grabar estas canciones en la base de datos
        // 4) Borrar el artista

        List<Song> canciones = songRepository.findByArtistId(id);

        List<Song> cancionesModificadas = canciones.stream()
                        .map(c -> {
                            c.setArtist(null);
                            return c;
                        }).collect(Collectors.toList());

        songRepository.saveAll(cancionesModificadas);

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
