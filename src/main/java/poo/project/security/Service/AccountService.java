package poo.project.security.Service;


import poo.project.security.Entities.AppRole;
import poo.project.security.Entities.AppUser;
import java.util.List;

public interface AccountService {
    void addUserWithRoles(AppUser user);
    void addRole(String role);
    void addRoleToUser(String email,String role);
    void removeRoleFromUser(String email,String role);
    AppUser loadUserByEmail(String email);
}
