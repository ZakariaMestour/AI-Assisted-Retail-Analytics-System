package poo.project.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class ProductsGroup {
    @Id
    private String id ;
    private  String name;
    //private  String category; ????
    private  Double price;
    private  Integer quantity;
    private  Integer threshold;
    @ManyToOne
    private Shelf shelf;

    @ManyToOne
    private Category category;

}
