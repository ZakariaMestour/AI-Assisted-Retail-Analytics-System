package poo.project.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poo.project.Security.Entities.AppUser;

import java.util.ArrayList;
import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor @Builder @Data
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @OneToMany(fetch = FetchType.EAGER)
    private List<AppUser> users=new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER) //puuush
    private List<Zone> zones=new ArrayList<>();

}
