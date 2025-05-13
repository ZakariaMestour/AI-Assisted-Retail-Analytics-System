package poo.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poo.project.Entities.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
    public Store findByName(String name);
}
