package poo.project.security.Repositoty;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import poo.project.security.Entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByEmail(String username);
    AppUser findAppUserByUserId(String id);
}
