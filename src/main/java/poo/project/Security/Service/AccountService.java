package poo.project.Security.Service;


import org.springframework.http.ResponseEntity;
import poo.project.Dto.AppUserDTO;
import poo.project.Exceptiions.RoleAlreadyExistsException;
import poo.project.Exceptiions.UserAlreadyExistsException;
import poo.project.Security.Entities.AppUser;
import poo.project.Utils.ApiResponse;

public interface AccountService {
    ResponseEntity<ApiResponse<AppUserDTO>> saveUserWithRoles(AppUserDTO userDTO) throws UserAlreadyExistsException, RoleAlreadyExistsException;
    String addRole(String role);
    void addRoleToUser(String email,String role);
    void removeRoleFromUser(String email,String role);
    AppUser loadUserByEmail(String email);
}
