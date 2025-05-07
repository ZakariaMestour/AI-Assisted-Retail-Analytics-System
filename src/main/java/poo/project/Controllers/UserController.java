package poo.project.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import poo.project.Security.Entities.AppUser;
import poo.project.Services.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")

public class UserController {

    private UserService userService;

    @GetMapping
    public List<AppUser> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public AppUser getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping
    public void editUser(AppUser user) {
        userService.editUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
    }
    
    @PostMapping
    public void addUser( @RequestBody  AppUser user) {
        userService.saveUser(user);
    }

}
