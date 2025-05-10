package poo.project.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poo.project.Dto.AppUserDTO;
import poo.project.Exceptiions.RoleAlreadyExistsException;
import poo.project.Exceptiions.UserAlreadyExistsException;
import poo.project.Exceptiions.UserNotFoundException;
import poo.project.Mappers.AccountMapperImp;
import poo.project.Utils.ApiResponse;
import poo.project.Security.Entities.AppUser;
import poo.project.Security.Repositoty.AppUserRepository;
import poo.project.Security.Service.AccountService;

import java.util.List;

@Transactional
@Service @AllArgsConstructor
public class UserService {

    private final AppUserRepository userRepository;
    private final AccountService accountService;
    private final AccountMapperImp accountMapper;

    public ResponseEntity<ApiResponse< List<AppUserDTO>>> getAllUsers(){
        List<AppUser> users = userRepository.findAll();
        List<AppUserDTO> usersDTO = users.stream().map(accountMapper::fromUser).toList();
        return  !usersDTO.isEmpty()? ResponseEntity.ok(new ApiResponse<>(true,"Users list",usersDTO)):ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false,"No user found",usersDTO));
    }

    public ResponseEntity<ApiResponse<AppUserDTO>> getUserById(String id) throws  UserNotFoundException {
        AppUser user =userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found"));
        return ResponseEntity.ok(new ApiResponse<>(true,"User found",accountMapper.fromUser(user)));
    }

    public ResponseEntity<ApiResponse<AppUserDTO>> saveUser(AppUserDTO user) throws UserAlreadyExistsException, RoleAlreadyExistsException {
        return accountService.saveUserWithRoles(user);
    }

    public  ResponseEntity<ApiResponse<Void>> deleteUserById(String id) throws UserNotFoundException {
        userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found"));
        userRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully"));
    }

    public ResponseEntity<ApiResponse<AppUserDTO>> editUser(AppUserDTO userDTO) throws UserAlreadyExistsException, RoleAlreadyExistsException {
        return accountService.saveUserWithRoles(userDTO);
    }


}
