package com.salesianostriana.dam.P01.controller;

import com.salesianostriana.dam.P01.model.Playlist;
import com.salesianostriana.dam.P01.model.Song;
import com.salesianostriana.dam.P01.dto.CreatePlaylistDto;
import com.salesianostriana.dam.P01.dto.PlaylistDtoConverter;
import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.repos.PlaylistRepository;
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


import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
@Tag(name = "Playlist", description = "Controlador de las Playlist")
public class PlaylistController {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final PlaylistDtoConverter playlistDtoConverter;
    private final PlaylistRepository repository;

    @Operation(summary = "Obtiene una lista de todas las playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la lista de playlist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado ninguna playlist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @GetMapping("/")
    public ResponseEntity<List<Playlist>> findAll() {

        if (playlistRepository.findAll().isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        }
        return ResponseEntity.ok().body(playlistRepository.findAll());
    }


    @Operation(summary = "Obtiene una playlist por la ID seleccionada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la playlist indicada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado la playlist indicada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> findOnePlaylist(@PathVariable Long id) {
        return ResponseEntity.of(playlistRepository.findById(id));
    }


    @Operation(summary = "Crea una nueva playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la playlst",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear la playlist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @PostMapping("/")
    public ResponseEntity<Playlist> create(@RequestBody CreatePlaylistDto dto) {
        if (dto.getSongId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Playlist nuevaPlaylist = playlistDtoConverter.createPlaylistDtoToPlaylist(dto);
        Song nuevaSong = songRepository.findById(dto.getSongId()).orElse(null);

        nuevaPlaylist.setSongs(List.of(nuevaSong));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playlistRepository.save(nuevaPlaylist));
    }


    @Operation(summary = "Modificar el contenido de una playlist previamente creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la playlist y se ha modificado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado la playlist indicado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @PutMapping("/{id}")
    public ResponseEntity<Playlist> edit(@RequestBody Playlist playlist, @PathVariable Long id) {


        return ResponseEntity.of(
                playlistRepository.findById(id).map(p -> {
                    p.setName(playlist.getName());
                    p.setDescription(playlist.getDescription());
                    return p;

                })
        );

    }


    @Operation(summary = "A??ade una nueva canci??n al playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la playlist y se ha modificado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @PostMapping("/{id1}/songs/{id2}")
    public ResponseEntity<Playlist> addSong(@RequestBody Playlist playlist,
                                            @PathVariable Long id1,
                                            @PathVariable Long id2) {
        Optional<Playlist> playlistActual = playlistRepository.findById(id1);
        Optional<Song> nuevaSong = songRepository.findById(id2);

        playlistActual.get().getSongs().add(nuevaSong.get());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playlistRepository.save(playlistActual.get()));
    }


    @Operation(summary = "Borra una playlist previamente creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),


            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la playlist indicado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        playlistRepository.deleteById(id);
        return ResponseEntity.noContent().build();


    }


    @Operation(summary = "Borra una canci??n de una playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la canci??n de la playlist correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la canci??n en la playlist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})

    @DeleteMapping("/{id1}/songs/{id2}")
    public ResponseEntity<?> deleteSongFromPlaylist(@RequestBody Playlist playlist,
                                                    @PathVariable Long id1,
                                                    @PathVariable Long id2) {
        Optional<Playlist> playlistActual = playlistRepository.findById(id1);

        playlistActual.get().getSongs().remove(songRepository.findById(id2).get());

        playlistRepository.save(playlistActual.get());

        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Muestras todas las canciones de una playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la playlist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la playlist indicado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @GetMapping("/{id}/songs")
    public ResponseEntity<Playlist> findPlaylistSongs(@PathVariable Long id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @Operation(summary = "Muestras todas las canciones de una playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la playlist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la playlist indicado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class))})})
    @GetMapping("/{id1}/songs/{id2}")
    public ResponseEntity<Stream<Song>> findOne(@PathVariable Long id1,
                                                @PathVariable Long id2) {

        return ResponseEntity
                .of(playlistRepository.findById(id1)
                        .map(m -> (m.getSongs().stream().filter(song -> song.getId().equals(id2)))
                        ));
    }
}
