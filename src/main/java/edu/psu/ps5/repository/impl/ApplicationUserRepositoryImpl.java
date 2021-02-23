package edu.psu.ps5.repository.impl;

import edu.psu.ps5.jpa.JpaApplicationUserRepository;
import edu.psu.ps5.model.ApplicationUser;
import edu.psu.ps5.repository.ApplicationUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

    private final JpaApplicationUserRepository jpaApplicationUserRepository;

    public ApplicationUserRepositoryImpl(JpaApplicationUserRepository jpaApplicationUserRepository) {
        this.jpaApplicationUserRepository = jpaApplicationUserRepository;
    }

    @Override
    public void addUser(ApplicationUser user) {
        jpaApplicationUserRepository.save(user);
    }

    @Override
    public ApplicationUser findByUserName(String username) {
        return jpaApplicationUserRepository.findByUsername(username);
    }
}
