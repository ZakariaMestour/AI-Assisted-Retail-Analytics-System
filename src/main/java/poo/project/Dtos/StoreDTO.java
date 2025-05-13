package poo.project.Dtos;

import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poo.project.Classes.DayHours;
import poo.project.Converter.ScheduleConverter;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class StoreDTO {
     Long id;
     String name;
     String email;
     String phoneNumber;
     String country;
     String address;
     private List<DayHours> schedule;
}
