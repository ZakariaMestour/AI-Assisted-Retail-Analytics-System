package poo.project.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poo.project.Classes.DayHours;
import poo.project.Converter.ScheduleConverter;
import poo.project.Security.Entities.AppUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity @AllArgsConstructor @NoArgsConstructor @Builder @Data
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String country;
    private String address;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "store")
    private List<AppUser> users=new ArrayList<>();
    @Convert(converter = ScheduleConverter.class)
    private List<DayHours> schedule;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Zone> zones=new ArrayList<>();

}
