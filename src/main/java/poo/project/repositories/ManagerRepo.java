package poo.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poo.project.entities.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {
}
