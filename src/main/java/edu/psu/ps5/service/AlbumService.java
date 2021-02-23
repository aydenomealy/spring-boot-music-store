package edu.psu.ps5.service;

import edu.psu.ps5.model.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAlbums();

    List<Album> searchAlbums(String term);

    void addAlbum(String albumName, String artistName, String releaseDate, String albumGenre, String numTracks, String price);

    void deleteAlbum(Long albumID);

    Album getAlbumByID(Long albumID);

    void editAlbum(String albumName, String artistName, String releaseDate, String albumGenre, String numTracks, String price, Long albumID);

    List<Album> searchArtist(String term);
}
