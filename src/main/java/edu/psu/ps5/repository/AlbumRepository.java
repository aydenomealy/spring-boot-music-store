package edu.psu.ps5.repository;

import edu.psu.ps5.model.Album;

import java.time.LocalDate;
import java.util.List;

public interface AlbumRepository {
    List<Album> getAlbums();

    void addAlbum(Album album);

    List<Album> searchAlbum(String term);

    Album getAlbumByID(Long albumID);

    void deleteAlbum(Long albumID);

    void editAlbum(String albumName, String artistName, LocalDate releaseDate, String albumGenre, String numTracks, String price, Long albumID);

    List<Album> searchArtist(String term);
}
