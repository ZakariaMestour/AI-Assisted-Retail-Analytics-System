package poo.project.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import poo.project.Security.Entities.AppRole;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class AppUserDTO {
    String userId;
    @NotBlank(message = "First name is required")
    String firstName;
    @NotBlank(message = "Last name is required")
    String lastName;
    @Email(message = "Invalid email format")
//    @NotBlank(message = "Email is required")
    String email;
    @NotBlank(message = "Phone number is required")
    String phoneNumber;
    List<AppRole> roles;

    public AppUserDTO( String firstName, String lastName, String email,String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
