package poo.project.Security.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import poo.project.Dtos.AppUserDTO;
import poo.project.Exceptiions.RoleAlreadyExistsException;
import poo.project.Exceptiions.RoleNotFoundException;
import poo.project.Exceptiions.UserAlreadyExistsException;
import poo.project.Exceptiions.UserNotFoundException;
import poo.project.Mappers.AccountMapperImp;
import poo.project.Utils.ApiResponse;
import poo.project.Security.Entities.AppRole;
import poo.project.Security.Entities.AppUser;
import poo.project.Security.Repositoty.AppRoleRepository;
import poo.project.Security.Repositoty.AppUserRepository;
import poo.project.Utils.PasswordUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AppRoleRepository roleRepository;
    private AppUserRepository userRepository;
    private final AccountMapperImp accountMapperImp;
    private final PasswordEncoder passwordEncoder;
    private final PasswordUtil passwordUtil;

    @Override
    public ResponseEntity<ApiResponse<AppUserDTO>> saveUserWithRoles(AppUserDTO userDTO) throws UserAlreadyExistsException, RoleNotFoundException {

        if (isEmailTaken(userDTO)) {
            System.out.println("Email taken");
            throw new UserAlreadyExistsException("This email is already in use by another user");
        }
            System.out.println("Email was not taken");

        //loop through list of roles and check for their existence
        List<AppRole> attachedRoles = new ArrayList<>();
        if (userDTO.getRoles() != null) {
            for (AppRole inputRole : userDTO.getRoles()) {
                AppRole appRole = roleRepository.findByRole(inputRole.getRole())
                        .orElseThrow(() -> new RoleNotFoundException("Role not found: " + inputRole.getRole()));
                attachedRoles.add(appRole);
            }
            userDTO.setRoles(attachedRoles);
        }

        //generate the id for new users
        userDTO.setUserId(UUID.randomUUID().toString());
        //map the dto user to an entity user
        AppUser newUser = accountMapperImp.fromUserDTO(userDTO);
        //generate a password
        newUser.setPassword(passwordEncoder.encode(passwordUtil.generatePassword(12)));
        // save the new user then remap it back to a dto user that will be sent to the font-end
        AppUserDTO savedUserDTO =  accountMapperImp.fromUser(userRepository.save(newUser));

        return ResponseEntity.ok(new ApiResponse<>(true,"User has been saved successfully",savedUserDTO));
    }

    @Override
    public ResponseEntity<ApiResponse<AppUserDTO>> updateUserWithRoles(AppUserDTO userDTO) throws  RoleAlreadyExistsException, UserNotFoundException {

        System.out.println("DTO" + userDTO);
        AppUser appUser =  userRepository.findById(userDTO.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));

        //loop through list of roles and check for their existence
        List<AppRole> attachedRoles = new ArrayList<>();
        if (userDTO.getRoles() != null) {
            for (AppRole inputRole : userDTO.getRoles()) {
                AppRole appRole = roleRepository.findByRole(inputRole.getRole())
                        .orElseThrow(() -> new RoleAlreadyExistsException("Role not found: " + inputRole.getRole()));
                attachedRoles.add(appRole);
            }
        }

        appUser = accountMapperImp.fromUserDTO(userDTO, appUser);
        appUser.setRoles(attachedRoles);
        userRepository.save(appUser);

        return ResponseEntity.ok(new ApiResponse<>(true,"User has been updated successfully",accountMapperImp.fromUser(appUser)));
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

    private boolean isEmailTaken(AppUserDTO userDTO) {
        AppUser existingUser = userRepository.findByEmail(userDTO.getEmail());
        return existingUser!=null && existingUser.getUserId().equals(userDTO.getUserId());
    }

}
