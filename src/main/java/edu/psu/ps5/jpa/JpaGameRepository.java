package edu.psu.ps5.jpa;

import edu.psu.ps5.model.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaGameRepository extends CrudRepository<Album, Long> {
    List<Album> findByAlbumNameIgnoreCaseContaining(String term);

    List<Album> findByArtistNameIgnoreCaseContaining(String term);
}
