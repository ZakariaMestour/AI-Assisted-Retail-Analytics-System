package poo.project.Security.Service;


import poo.project.Security.Entities.AppUser;

public interface AccountService {
    void addUserWithRoles(AppUser user);
    String addRole(String role);
    void addRoleToUser(String email,String role);
    void removeRoleFromUser(String email,String role);
    AppUser loadUserByEmail(String email);
}
