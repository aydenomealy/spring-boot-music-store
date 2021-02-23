package edu.psu.ps5.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Album {

    //Instance Data
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_id_seq")
    @SequenceGenerator(name = "album_id_seq", sequenceName = "album_id_seq", allocationSize = 100)
    private long albumId;
    private String albumName;
    private String artistName;
    private LocalDate dateReleased;
    private String genre;
    private int numTracks;
    private double price;

    //Constructor
    public Album(String albumName, String artistName, LocalDate dateReleased, String genre, int numTracks, double price) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.dateReleased = dateReleased;
        this.genre = genre;
        this.numTracks = numTracks;
        this.price = price;
    }

    public Album() {
    }

    //Getters & Setters
    public long getAlbumID() {
        return albumId;
    }

    public void setAlbumID(long albumID) {
        this.albumId = albumID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public LocalDate getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(LocalDate dateReleased) {
        this.dateReleased = dateReleased;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNumTracks() {
        return numTracks;
    }

    public void setNumTracks(int numTracks) {
        this.numTracks = numTracks;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
