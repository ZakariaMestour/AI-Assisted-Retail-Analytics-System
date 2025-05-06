package poo.project.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import poo.project.security.Entities.AppRole;
import poo.project.security.Entities.AppUser;
import poo.project.security.Repositoty.AppUserRepository;
import poo.project.security.Service.AccountService;

import java.util.List;
import java.util.UUID;

@Service @AllArgsConstructor
public class UserService {
    private AppUserRepository userRepository;
    private AccountService accountService;

    public List<AppUser> getAllUsers(){
        return userRepository.findAll();
    }
    public void deleteUserById(String id){
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveUser(AppUser user){
        accountService.addUserWithRoles(user);
    }

    public void editUser(AppUser user){
        userRepository.save(user);

    }

    public AppUser getUserById(String id){
        return userRepository.findAppUserByUserId(id);
    }

}
