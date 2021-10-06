package com.salesianostriana.dam.P01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class GetSongDto {

    private String title;
    private String album;
    private String year;
    private String artist;
}
