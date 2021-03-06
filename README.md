# **API TRIANAFY**

                     ___________  _______    __          __      _____  ___        __       _______  ___  ___  
                    ("     _   ")/"      \  |" \        /""\    (\"   \|"  \      /""\     /"     "||"  \/"  | 
                     )__/  \\__/|:        | ||  |      /    \   |.\\   \    |    /    \   (: ______) \   \  /  
                        \\_ /   |_____/   ) |:  |     /' /\  \  |: \.   \\  |   /' /\  \   \/    |    \\  \/   
                        |.  |    //      /  |.  |    //  __'  \ |.  \    \. |  //  __'  \  // ___)    /   /    
                        \:  |   |:  __   \  /\  |\  /   /  \\  \|    \    \ | /   /  \\  \(:  (      /   /     
                         \__|   |__|  \___)(__\_|_)(___/    \___)\___|\____\)(___/    \___)\__/     |___/      

                                            ,_______________________________________,
                                            /   HHH*                          *HHH   /.
                                            `---------------------------------------" :
                                            | +              2DAM 21/22           + | :
                                            |             .____________.            | :
                                            |       .++.  |######\  /##|  .++.      | :
                                            |       +  +  |######/  \##|  +  +      | :
                                            |       '++'  '------------'  '++'      | :
                                            |                                       | :
                                            |                                       | :
                                            |                                       | :
                                            |      /'''''''''''''''''''''''''\      I :
                                            |     /     o       0         o   \     I :
                                            |+   / O                         O \   +|,'
                                            `---------------------------------------`


## Desarrollada por :

### Grupo 1
***
#### - Sergio Chamorro Garc??a
#### - Manuel Caro Delgado
#### - Guillermo Ferrari Ferrari
#### - Mois??s Miranda Corrales
***

Esta API REST permite controlar el funcionamiento de la app Trianafy, la cual gestiona informaci??n musical. 
Esto incluye las siguientes funcionalidades:

- Las peticiones que se detallan m??s abajo.
- La docuementaci??n generada con OpenApi 3.0 y Swagger.
- Una colecci??n de Postman con las peticiones generadas en JSON para poder realizar pruebas de la app.

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

Por otro lado, para el correcto manejo de las asociaciones entre entidades, se han creado diferentes DTOs (Data Transfer Object); estos nos permiten crear nuevos objetos con los atributos de las entidades que m??s nos interesen.

En nuestra API, se han creado los siguientes DTOs:

### Song

- **GetSongDto** : Nos permite obtener los datos de esta pseudoentidad.
- **CreateSongDto** : Nos permite generar el DTO con los datos necesarios.
- **PutSongDto** : Permite editar los datos del DTO previamente creado.
- **SongDtoConverter** : Contiene m??todos que nos permiten convertir una entidad Song en DTO y viceversa.

### Playlist

- **GetPlaylistDto**
- **CreatePlaylistDto**
- **PlaylistDtoConverter**

Las peticiones que se han definido en nuestra API son:

### Artist

- **A??adir artista** : Petici??n tipo POST.
- **Ver todos los artistas** : Petici??n tipo GET.
- **Buscar artista por ID** : Petici??n tipo GET.
- **Editar artista** : Petici??n tipo PUT.
- **Eliminar arista** : Petici??n tipo DELETE.

### Song

- **A??adir canci??n** : Petici??n tipo POST.
- **Ver todos las canciones** : Petici??n tipo GET.
- **Buscar canci??n por ID** : Petici??n tipo GET.
- **Editar canci??n** : Petici??n tipo PUT.
- **Eliminar canci??n** : Petici??n tipo DELETE.

### Playlist

- **A??adir playlist** : Petici??n tipo POST.
- **Ver todos las playlist** : Petici??n tipo GET.
- **Buscar plyalist por ID** : Petici??n tipo GET.
- **Editar playlist** : Petici??n tipo PUT.
- **Eliminar playlist** : Petici??n tipo DELETE.
- **A??adir canci??n a playlist** : Petici??n tipo POST.
- **Ver todas las canciones de una playlist** : Petici??n tipo GET.
- **Buscar una canci??n de una playlist por su ID** : Petici??n tipo GET.
- **Eliminar una canci??n de una playlist** : Petici??n tipo DELETE.
