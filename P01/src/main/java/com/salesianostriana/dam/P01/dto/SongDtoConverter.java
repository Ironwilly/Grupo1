package com.salesianostriana.dam.P01.dto;

import com.salesianostriana.dam.P01.model.Artist;
import com.salesianostriana.dam.P01.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SongDtoConverter {

    public Song createSongDtoToSong(CreateSongDto dto){
        return new Song(
                dto.getTitle(),
                dto.getAlbum(),
                dto.getYear()
        );
    }

    public Song editSongDtoToSong(PutSongDto dto){
        return new Song(
                dto.getTitle(),
                dto.getArtist(),
                dto.getAlbum(),
                dto.getYear()

        );
    }

    public GetSongDto SongToSongDto(Song s){
        return GetSongDto
                .builder()
                .title(s.getTitle())
                .album(s.getAlbum())
                .year(s.getYear())
                .artist(s.getArtist().getName())
                .build();
    }
}
