package poo.project.Security.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poo.project.Entities.Store;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AppUser {
    @Id
    private String userId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String password;
    @Column(unique=true)
    private String email;
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    public List<AppRole> roles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Store store;


}
