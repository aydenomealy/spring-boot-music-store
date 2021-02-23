package edu.psu.ps5.service.security;

import edu.psu.ps5.model.ApplicationUser;
import edu.psu.ps5.model.security.SecurityUser;
import edu.psu.ps5.service.ApplicationUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final ApplicationUserService applicationUserService;

    public ApplicationUserDetailsService(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserService.findByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException("username not found " + username);
        }

        return new SecurityUser(user);
    }
}
