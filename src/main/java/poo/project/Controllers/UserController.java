package poo.project.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poo.project.Security.Entities.AppRole;
import poo.project.Security.Entities.AppUser;
import poo.project.Security.Service.AccountService;
import poo.project.Services.UserService;
import poo.project.Utils.ApiResponse;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private AccountService accountService;
    private UserService userService;

    @GetMapping
    public List<AppUser> getAllUsers() {
        return this.userService.getAllUsers();
    }
    @PostMapping
    public AppUser addUser(@RequestBody AppUser user) {
        userService.saveUser(user);
        return user;
    }


    @PostMapping("/addRole")
    public ResponseEntity<?> addRole(@RequestBody AppRole roleObj) {
        String role = roleObj.getRole();
        accountService.addRole(role);

        return ResponseEntity.ok(new ApiResponse<>(true,"Role has been added",null));
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        try {
            userService.deleteUserById(id);
            return "User deleted successfully";
        } catch (Exception e) {
            return "Error deleting user: " ;
        }
    }
    @PutMapping
    public void editUser(@RequestBody AppUser user) {
        userService.editUser(user);
    }






    @GetMapping("/{id}")
    public AppUser getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }





}
