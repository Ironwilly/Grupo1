# **API TRIANAFY**

## Desarrollada por :

### Grupo 1
***
#### - Sergio Chamorro García
#### - Manuel Caro Delgado
#### - Guillermo Ferrari Ferrari
#### - Moisés Miranda Corrales
***

Esta API REST permite controlar el funcionamiento de la app Trianafy.

## Entidades

Las entidades que componen esta API son:

- Artist
- Song
- Playlist

Para el correcto manejo de cada entidad contamos con las siguientes clases:

### Artist

- **ArtistController**

    Peticiones que nos permiten realizar el CRUD de la entidad.

- **ArtistRepository**

    Repositorio que nos permite guardar los artistas creados.

### Song

- **SongController**
- **SongRepository**

### Playlist

- **PlaylistController**
- **PlaylistRepository**

Por otro lado, para el correcto manejo de las asociaciones entre entidades, se han creado diferentes DTOs (Data Transfer Object); estos nos permiten crear nuevos objetos con los atributos de las entidades que más nos interesen.

En nuestra API, se han creado los siguientes DTOs:

### Song

- **GetSongDto** : Nos permite obtener los datos de esta pseudoentidad.
- **CreateSongDto** : Nos permite generar el DTO con los datos necesarios.
- **PutSongDto** : Permite editar los datos del DTO previamente creado.
- **SongDtoConverter** : Contiene métodos que nos permiten convertir una entidad Song en DTO y viceversa.

### Playlist

- **GetPlaylistDto**
- **CreatePlaylistDto**
- **PlaylistDtoConverter**

Las peticiones que se han definido en nuestra API son:

### Artist

- **Añadir artista** : Petición tipo POST.
- **Ver todos los artistas** : Petición tipo GET.
- **Buscar artista por ID** : Petición tipo GET.
- **Editar artista** : Petición tipo PUT.
- **Eliminar arista** : Petición tipo DELETE.
