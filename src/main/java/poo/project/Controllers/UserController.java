package poo.project.Controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poo.project.Dto.AppUserDTO;
import poo.project.Exceptiions.RoleAlreadyExistsException;
import poo.project.Exceptiions.RoleNotFoundException;
import poo.project.Exceptiions.UserAlreadyExistsException;
import poo.project.Exceptiions.UserNotFoundException;
import poo.project.Security.Service.AccountService;
import poo.project.Security.Service.AccountServiceImpl;
import poo.project.Services.UserService;
import poo.project.Utils.ApiResponse;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AccountService accountService;
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse< List<AppUserDTO>>> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AppUserDTO>> saveUser(@Valid @RequestBody AppUserDTO userDTO) throws UserAlreadyExistsException, RoleNotFoundException {
        return userService.saveUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String id) throws UserNotFoundException  {
            return userService.deleteUserById(id);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<AppUserDTO>> editUser(@Valid @RequestBody AppUserDTO userDTO) throws UserNotFoundException, RoleAlreadyExistsException {
        return userService.updateUser(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppUserDTO>> getUser(@PathVariable String id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping("/addRole")
    public String addRole(@RequestBody String role){
        return accountService.addRole(role) ;
    }
}
