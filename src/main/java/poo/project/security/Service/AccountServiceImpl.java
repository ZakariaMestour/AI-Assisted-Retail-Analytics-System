package poo.project.security.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import poo.project.security.Entities.AppRole;
import poo.project.security.Entities.AppUser;
import poo.project.security.Repositoty.AppRoleRepository;
import poo.project.security.Repositoty.AppUserRepository;

import java.util.UUID;
@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AppUserRepository appUserRepository;
    AppRoleRepository roleRepository;
    AppUserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public AppUser addUser(String username, String password, String email, String confirmPassword) {
        AppUser user = userRepository.findByUsername(username);
        if (user != null) throw new RuntimeException("This user already exists");
        if (!password.equals(confirmPassword)) throw new RuntimeException("Passwords do not match");

        user = AppUser.builder().userId(UUID.randomUUID().toString()).username(username).password(passwordEncoder.encode(password)).email(email).build();
        return userRepository.save(user);
    }

    @Override
    public AppRole addRole(String role) {
        AppRole appRole = roleRepository.findByRole(role).orElse(null);
        if (appRole != null) throw new RuntimeException("This role already exists");
        appRole = AppRole.builder().id(UUID.randomUUID().toString()).role(role).build();
        return roleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser user = userRepository.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");
        AppRole appRole =  roleRepository.findByRole(role).orElse(null);
        if (appRole == null) throw new RuntimeException("Role not found");
        user.roles.add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser user = userRepository.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");
        AppRole appRole =  roleRepository.findById(role).orElse(null);
        if (appRole == null) throw new RuntimeException("Role not found");
        user.roles.remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
