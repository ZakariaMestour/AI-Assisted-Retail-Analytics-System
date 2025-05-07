package poo.project.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poo.project.Security.Entities.AppUser;
import poo.project.Security.Repositoty.AppUserRepository;
import poo.project.Security.Service.AccountService;

import java.util.List;


@Transactional
@AllArgsConstructor

@Service
public class UserService {
    private AppUserRepository userRepository;
    private AccountService accountService;

    public List<AppUser> getAllUsers()
    {
        return userRepository.findAll();
    }

    public void deleteUserById(String id){
        userRepository.deleteById(id);
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
