package poo.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import poo.project.Dto.AppUserDTO;
import poo.project.Exceptiions.RoleAlreadyExistsException;
import poo.project.Exceptiions.RoleNotFoundException;
import poo.project.Exceptiions.UserAlreadyExistsException;
import poo.project.Security.Service.AccountService;
import poo.project.Services.UserService;

import java.util.stream.Stream;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner userDetailsManger(UserService userService, AccountService accountService) {
        return args ->{
            Stream.of("Manager","Admin","Sub Manager").forEach(accountService::addRole);

            Stream.of("Houssam","Imran","Wafae","Zakaria").forEach(name -> {
                try {
                    userService.saveUser(new AppUserDTO(name,name,name+"@gmail.com",String.valueOf(Math.round(Math.random()*100000000))));

                } catch (UserAlreadyExistsException | RoleNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        };
    }

    @Bean
    PasswordEncoder PasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }


}
