package poo.project.Dto.Responses;

import java.util.List;

public record UserResponse(
        String userId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        List<String> roles
) {
}
