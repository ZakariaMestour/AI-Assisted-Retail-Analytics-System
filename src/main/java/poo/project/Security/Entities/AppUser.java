package poo.project.Security.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AppUser {
    @Id
    private String userId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String password;
    @Column(unique=true)
    private String email;
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    public List<AppRole> roles = new ArrayList<>();
}
