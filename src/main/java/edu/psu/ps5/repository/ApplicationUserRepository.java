package edu.psu.ps5.repository;

import edu.psu.ps5.model.ApplicationUser;

public interface ApplicationUserRepository {

    void addUser(ApplicationUser user1);

    ApplicationUser findByUserName(String username);
}
