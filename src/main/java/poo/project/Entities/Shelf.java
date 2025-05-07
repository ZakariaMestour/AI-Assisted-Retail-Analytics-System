package poo.project.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor @Builder @Data
public class Shelf {
    @Id
    private String id;
    @OneToMany
    List<ProductsGroup> productsGroups=new ArrayList<>();
}
