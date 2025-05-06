package poo.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import poo.project.security.Repositoty.AppRoleRepository;
import poo.project.security.Service.AccountService;
import java.security.SecureRandom;
@SpringBootApplication
public class Main {
    @Autowired
    private AppRoleRepository roleRepository;
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

//    @Bean
    CommandLineRunner userDetailsManger(AccountService accountService) {
        return args -> {
            //accountService.addRole("MANGER");
            //accountService.addUser("karim","samiri","1234", "1234","karim@gmail.com");
//            accountService.addRoleToUser("karim@gmail.com","MANAGER");

//            roleRepository.findAll().forEach(System.out::println);
        };
    }

    @Bean
    PasswordEncoder PasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }


}
