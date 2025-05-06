package poo.project.security.Service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import poo.project.security.Entities.AppRole;
import poo.project.security.Entities.AppUser;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = accountService.loadUserByEmail(email);
        if (user == null) throw new UsernameNotFoundException(String.format("User %s not found", email));

        UserDetails userDetails = User
                .withUsername(email)
                .password(user.getPassword())
                .roles(user.roles.stream().map(AppRole::getRole).toArray(String[]::new))
                .build();
        return userDetails;
    }
}
