package poo.project.Services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import poo.project.Utils.PasswordUtil;
import poo.project.Security.Entities.AppRole;
import poo.project.Security.Entities.AppUser;
import poo.project.Security.Repositoty.AppRoleRepository;
import poo.project.Security.Repositoty.AppUserRepository;
import poo.project.Security.Service.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service @AllArgsConstructor
public class UserService {
    private AppRoleRepository appRoleRepository;
    private AppUserRepository userRepository;
    private AccountService accountService;
    private PasswordEncoder passwordEncoder;
    public List<AppUser> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser(AppUser user){
        accountService.addUserWithRoles(user);
    }


    @Transactional
    public void deleteUserById(String id){
        userRepository.deleteById(id);

    }

    public void editUser(AppUser user){
        List<AppRole> attachedRoles = new ArrayList<>();
        if (user.roles != null) {
            for (AppRole inputRole : user.roles) {
                AppRole appRole = appRoleRepository.findByRole(inputRole.getRole())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + inputRole.getRole()));
                attachedRoles.add(appRole);
            }
        }
        AppUser newUser = AppUser.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(attachedRoles)
                .build();

        userRepository.save(newUser);
    }

    public AppUser getUserById(String id){
        return userRepository.findAppUserByUserId(id);
    }

}
