package edu.psu.ps5.controller;

import edu.psu.ps5.model.Album;
import edu.psu.ps5.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    private final AlbumService albumService;

    public IndexController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Album> albumList = albumService.getAlbums();
        model.addAttribute("albumList", albumList);
        return "index";
    }

    @PostMapping("/")
    public String addAlbumForm(@RequestParam String albumName, @RequestParam String artistName, @RequestParam String releaseDate, @RequestParam String albumGenre, @RequestParam String numTracks, @RequestParam String price,  Model model) {
        List<Album> albumList = new ArrayList<>();
        try {
            albumService.addAlbum(albumName, artistName, releaseDate, albumGenre, numTracks, price);

            albumList = albumService.getAlbums();

            model.addAttribute("successMessage", "Your album has been successfully added");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("albumList", albumList);
        return "index";
    }

    @PostMapping("/contact")
    public String addContactForm(@RequestParam String requesterName, @RequestParam String requesterEmail, @RequestParam String textSubject, @RequestParam String textBody,  Model model) {
        try {
            //Send to email server or something

            model.addAttribute("successMessage", "Your email has been successfully sent");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "contact";
    }

    @PostMapping("edit-album")
    public String editAlbumForm(@RequestParam String albumName, @RequestParam String artistName, @RequestParam String releaseDate, @RequestParam String albumGenre, @RequestParam String numTracks, @RequestParam String price, @RequestParam Long albumID, Model model){
        try {
            albumService.editAlbum(albumName, artistName, releaseDate, albumGenre, numTracks, price, albumID);
            
            model.addAttribute("successMessage", "Changes to the album were successfully saved.");
            model.addAttribute("albumList", albumService.getAlbums());

            return "index";
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("album", albumService.getAlbumByID(albumID));
        }

        return "edit";
    }

    @PostMapping("/nameSearch")
    public String nameSearch(@RequestParam String term, Model model) {
        List<Album> albumList = new ArrayList<>();
        try {
            albumList = albumService.searchAlbums(term);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("albumList", albumList);
        return "index";
    }

    @PostMapping("/artistSearch")
    public String artistSearch(@RequestParam String term, Model model) {
        List<Album> albumList = new ArrayList<>();
        try {
            albumList = albumService.searchArtist(term);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("albumList", albumList);
        return "index";
    }

    @GetMapping("/albums/edit/{albumID}")
    public String editPage(@PathVariable Long albumID, Model model) {
        Album album = albumService.getAlbumByID(albumID);
        model.addAttribute("album", album);

        return "edit";
    }

    @GetMapping("/albums/delete/{albumID}")
    public String deletePage(@PathVariable Long albumID, Model model) {
        List<Album> albumList = new ArrayList<>();
        try {

            albumService.deleteAlbum(albumID);

            albumList = albumService.getAlbums();

            model.addAttribute("successMessage", "Your album has been successfully deleted");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("albumList", albumList);
        return "index";
    }
}
