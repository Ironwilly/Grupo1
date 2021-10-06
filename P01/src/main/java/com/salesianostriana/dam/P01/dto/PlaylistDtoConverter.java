package com.salesianostriana.dam.P01.dto;

import com.salesianostriana.dam.P01.model.Playlist;
import org.springframework.stereotype.Component;

@Component
public class PlaylistDtoConverter {

    public Playlist createPlaylistDtoToPlaylist(CreatePlaylistDto dto){
        return  new Playlist(
                dto.getName(),
                dto.getDescription()
        );
    }

    public  GetPlaylistDto playlistToPlaylistDto(Playlist p){
        return GetPlaylistDto
                .builder()
                .name(p.getName())
                .description(p.getDescription())
                .build();
    }
}
