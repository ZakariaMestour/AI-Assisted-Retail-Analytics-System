package poo.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poo.project.entities.SubManager;

public interface SubManagerRepo extends JpaRepository<SubManager, Integer> {
}
