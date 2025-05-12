package poo.project.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class StoreDTO {
     Long id;
     String name;
     String email;
     String phoneNumber;
     String country;
     String address;
     Object workHours;
}
