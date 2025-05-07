package poo.project.Security.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import poo.project.Utils.PasswordUtil;
import poo.project.Security.Entities.AppRole;
import poo.project.Security.Entities.AppUser;
import poo.project.Security.Repositoty.AppRoleRepository;
import poo.project.Security.Repositoty.AppUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AppRoleRepository roleRepository;
    private AppUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUserWithRoles(AppUser user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("This user already exists");
        }


        List<AppRole> attachedRoles = new ArrayList<>();
        if (user.roles != null) {
            for (AppRole inputRole : user.roles) {
                AppRole appRole = roleRepository.findByRole(inputRole.getRole())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + inputRole.getRole()));
                attachedRoles.add(appRole);
            }
        }

        AppUser newUser = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(PasswordUtil.generatePassword(12)))
                .roles(attachedRoles)
                .build();

        userRepository.save(newUser);
    }

    @Override
    public String addRole(String role) {
        System.out.println("method role: " + role);
        AppRole appRole = roleRepository.findByRole(role).orElse(null);
        if (appRole != null) throw new RuntimeException("This role already exists");
        appRole = AppRole.builder().id(UUID.randomUUID().toString()).role(role).build();
        roleRepository.save(appRole);
        return "role has been added";
    }


    @Override
    public void addRoleToUser(String email, String role) {
        AppUser user = userRepository.findByEmail(email);
        if (user == null) throw new RuntimeException("User not found");
        AppRole appRole =  roleRepository.findByRole(role).orElse(null);
        if (appRole == null) throw new RuntimeException("Role not found");
        user.roles.add(appRole);
    }

    @Override
    public void removeRoleFromUser(String email, String role) {
        AppUser user = userRepository.findByEmail(email);
        if (user == null) throw new RuntimeException("User not found");
        AppRole appRole =  roleRepository.findById(role).orElse(null);
        if (appRole == null) throw new RuntimeException("Role not found");
        user.roles.remove(appRole);
    }

    @Override
    public AppUser loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
