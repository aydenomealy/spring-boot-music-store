package edu.psu.ps5.service.impl;

import edu.psu.ps5.model.Album;
import edu.psu.ps5.repository.AlbumRepository;
import edu.psu.ps5.service.AlbumService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepository.getAlbums();
    }

    @Override
    public List<Album> searchAlbums(String term) {
        if (term == null || term.isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be empty");
        }

        return albumRepository.searchAlbum(term);
    }

    @Override
    public List<Album> searchArtist(String term) {
        if (term == null || term.isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be empty");
        }

        return albumRepository.searchArtist(term);
    }

    @Override
    public void addAlbum(String albumName, String artistName, String releaseDate, String albumGenre, String numTracks, String price) {
        double albumPrice;
        int trackNum;
        LocalDate date;

        if (albumName == null || albumName.isEmpty()) {
            throw new IllegalArgumentException("Album name is required");
        }
        if (artistName == null || artistName.isEmpty()) {
            throw new IllegalArgumentException("Artist name is required");
        }
        if (releaseDate == null || releaseDate.isEmpty()) {
            throw new IllegalArgumentException("Release date is required");
        }
        if (albumGenre == null || albumGenre.isEmpty()) {
            throw new IllegalArgumentException("Album genre is required");
        }
        if (numTracks == null || numTracks.isEmpty()) {
            throw new IllegalArgumentException("Number of tracks is required");
        }
        if (price == null || price.isEmpty()) {
            throw new IllegalArgumentException("Price of album is required");
        } else {
            try {
                albumPrice = Double.parseDouble(price);
            } catch (Exception e) {
                throw new IllegalArgumentException("Album Price is incorrect");
            }
            try {
                trackNum = Integer.parseInt(numTracks);
            } catch (Exception e) {
                throw new IllegalArgumentException("Number of tracks is incorrect");
            }
            try {
                String[] dateParts = releaseDate.split("/");
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int day = Integer.parseInt(dateParts[2]);

                date = LocalDate.of(year, month, day);
            } catch (Exception e) {
                throw new IllegalArgumentException("Date format is incorrect");
            }
        }

        Album album = new Album(albumName, artistName, date, albumGenre, trackNum, albumPrice);
        albumRepository.addAlbum(album);
    }

    @Override
    public void deleteAlbum(Long albumID) {
        if (albumID == null || albumID < 0) {
            throw new IllegalArgumentException("Game ID is required");
        }

        Album album = albumRepository.getAlbumByID(albumID);
        if (album == null || album.getAlbumID() < 0) {
            throw new IllegalArgumentException("Album with ID not found ->" + albumID);
        }

        albumRepository.deleteAlbum(albumID);
    }

    @Override
    public Album getAlbumByID(Long albumID) {
        if (albumID == null || albumID <= 0) {
            throw new IllegalArgumentException("Album ID is required");
        }

        Album album = albumRepository.getAlbumByID(albumID);
        if (album == null) {
            throw new IllegalStateException(String.format("Album with id %s is not found", albumID));
        }

        return album;
    }

    @Override
    public void editAlbum(String albumName, String artistName, String releaseDate, String albumGenre, String numTracks, String price, Long albumID) {
        double albumPrice;
        int trackNum;
        LocalDate date;

        if (albumID == null || albumID <= 0) {
            throw new IllegalArgumentException("Album ID is required");
        }
        if (albumName == null || albumName.isEmpty()) {
            throw new IllegalArgumentException("Album name is required");
        }
        if (artistName == null || artistName.isEmpty()) {
            throw new IllegalArgumentException("Artist name is required");
        }
        if (releaseDate == null || releaseDate.isEmpty()) {
            throw new IllegalArgumentException("Release date is required");
        }
        if (albumGenre == null || albumGenre.isEmpty()) {
            throw new IllegalArgumentException("Album genre is required");
        }
        if (numTracks == null || numTracks.isEmpty()) {
            throw new IllegalArgumentException("Number of tracks is required");
        }
        if (price == null || price.isEmpty()) {
            throw new IllegalArgumentException("Price of album is required");
        } else {
            try {
                albumPrice = Double.parseDouble(price);
            } catch (Exception e) {
                throw new IllegalArgumentException("Album Price is incorrect");
            }
            try {
                trackNum = Integer.parseInt(numTracks);
            } catch (Exception e) {
                throw new IllegalArgumentException("Number of tracks is incorrect");
            }
            try {
                String[] dateParts = releaseDate.split("-");
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int day = Integer.parseInt(dateParts[2]);

                date = LocalDate.of(year, month, day);
            } catch (Exception e) {
                throw new IllegalArgumentException("Date format is incorrect");
            }
        }
        albumRepository.editAlbum(albumName, artistName, date, albumGenre, numTracks, price, albumID);
    }
}
