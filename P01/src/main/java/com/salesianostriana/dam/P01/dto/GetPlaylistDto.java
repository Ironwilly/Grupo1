package com.salesianostriana.dam.P01.dto;


import com.salesianostriana.dam.P01.model.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPlaylistDto {

    private String name;
    private String description;
    List<Song> listSongs;


}