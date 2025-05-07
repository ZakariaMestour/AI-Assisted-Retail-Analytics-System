package poo.project.Security.Repositoty;


import org.springframework.data.jpa.repository.JpaRepository;
import poo.project.Security.Entities.AppRole;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
    Optional<AppRole> findByRole(String role);
}
