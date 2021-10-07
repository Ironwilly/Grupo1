package com.salesianostriana.dam.P01.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPlaylistDto {

    private String name;
    private String description;

import com.salesianostriana.dam.P01.model.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class GetPlaylistDto {

    List<Song> listSongs;



}
