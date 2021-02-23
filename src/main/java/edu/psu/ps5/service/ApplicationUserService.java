package edu.psu.ps5.service;

import edu.psu.ps5.model.ApplicationUser;

public interface ApplicationUserService {
    ApplicationUser findByUserName(String username);
}
