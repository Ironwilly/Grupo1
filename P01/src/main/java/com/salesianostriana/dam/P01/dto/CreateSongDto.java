package com.salesianostriana.dam.P01.dto;

import com.salesianostriana.dam.P01.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CreateSongDto {

    private String title;
    private String album;
    private String year;
    private Long artistId;
}
