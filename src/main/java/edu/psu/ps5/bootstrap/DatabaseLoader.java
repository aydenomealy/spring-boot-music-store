package edu.psu.ps5.bootstrap;

import edu.psu.ps5.model.Album;
import edu.psu.ps5.model.ApplicationUser;
import edu.psu.ps5.repository.AlbumRepository;
import edu.psu.ps5.repository.ApplicationUserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final AlbumRepository albumRepository;
    private final ApplicationUserRepository applicationUserRepository;

    public DatabaseLoader(AlbumRepository albumRepository, ApplicationUserRepository applicationUserRepository) {
        this.albumRepository = albumRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        albumRepository.addAlbum(new Album("Rubber soul", "The Beatles", LocalDate.of(1965,12,3), "Psychedelic Rock", 14, 15.99));
        albumRepository.addAlbum(new Album("Aja", "Steely Dan", LocalDate.of(1977,10, 17), "Jazz Rock", 7, 9.99));
        albumRepository.addAlbum(new Album("Illinois", "Sufjan Stevens", LocalDate.of(2005, 6, 4), "Folk Rock", 22, 19.99));
        albumRepository.addAlbum(new Album("K.G.", "King Gizzard and The Lizard Wizard", LocalDate.of(2020, 11, 20), "Psychedelic Rock", 10, 12.99));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        ApplicationUser user1 = new ApplicationUser("admin", encoder.encode("secretpassword"), true);
        ApplicationUser user2 = new ApplicationUser("fred", encoder.encode("123456"), false);

        applicationUserRepository.addUser(user1);
        applicationUserRepository.addUser(user2);
    }
}
