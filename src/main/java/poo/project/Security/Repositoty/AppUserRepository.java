package poo.project.Security.Repositoty;


import org.springframework.data.jpa.repository.JpaRepository;
import poo.project.Security.Entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByEmail(String username);
}
