package com.salesianostriana.dam.P01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaylistDto {

    private String name;
    private String description;
    private Long songId;
}
