package edu.psu.ps5.repository.impl;

import edu.psu.ps5.jpa.JpaGameRepository;
import edu.psu.ps5.model.Album;
import edu.psu.ps5.repository.AlbumRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepositoryImpl implements AlbumRepository {

    private final JpaGameRepository jpaGameRepository;

    public AlbumRepositoryImpl(JpaGameRepository jpaGameRepository) {
        this.jpaGameRepository = jpaGameRepository;
    }

    @Override
    public List<Album> getAlbums() {
        return (List<Album>) jpaGameRepository.findAll();
    }

    @Override
    public void addAlbum(Album album) {

       jpaGameRepository.save(album);
    }

    @Override
    public List<Album> searchAlbum(String term) {
       return jpaGameRepository.findByAlbumNameIgnoreCaseContaining(term);
    }

    @Override
    public List<Album> searchArtist(String term) {
        return jpaGameRepository.findByArtistNameIgnoreCaseContaining(term);
    }

    @Override
    public Album getAlbumByID(Long albumID) {
        Optional<Album> found = jpaGameRepository.findById(albumID);
        if(found.isPresent()) {
            return found.get();
        } else {
            throw new IllegalArgumentException("Album with ID " + albumID + " is not found");
        }
    }

    @Override
    public void deleteAlbum(Long albumID) {
        jpaGameRepository.deleteById(albumID);
    }

    @Override
    public void editAlbum(String albumName, String artistName, LocalDate releaseDate, String albumGenre, String numTracks, String price, Long albumID) {
        Album album = getAlbumByID(albumID);

        if (album == null) {
            throw new IllegalArgumentException(String.format("Album with id %s is not found", albumID));
        }

        album.setAlbumName(albumName);
        album.setArtistName(artistName);
        album.setDateReleased(releaseDate);
        album.setGenre(albumGenre);
        album.setNumTracks(Integer.parseInt(numTracks));
        album.setPrice(Double.parseDouble(price));

        jpaGameRepository.save(album);
    }
}
