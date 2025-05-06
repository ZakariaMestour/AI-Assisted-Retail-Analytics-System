package poo.project.controllers;


import org.springframework.web.bind.annotation.*;
import poo.project.security.Entities.AppUser;
import poo.project.security.Service.AccountService;
import poo.project.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private UserController(AccountService accountService,UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<AppUser> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/user")
    public AppUser getUser(@RequestParam String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/editUser")
    public void editUser(AppUser user) {
        userService.editUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam String id) {
        userService.deleteUserById(id);
    }
    
    @PostMapping("/addUser")
    public AppUser addUser(@RequestBody AppUser user) {
        userService.saveUser(user);
        return user;
    }

}
