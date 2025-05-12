package poo.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poo.project.Entities.Store;

public interface storeRepository extends JpaRepository<Store, Long> {
}
