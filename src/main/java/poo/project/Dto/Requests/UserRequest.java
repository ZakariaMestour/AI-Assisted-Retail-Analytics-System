package poo.project.Dto.Requests;

import jakarta.persistence.Column;

import org.antlr.v4.runtime.misc.NotNull;
import poo.project.Security.Entities.AppRole;


import java.util.List;

public record UserRequest(
    @NotNull
    String firstName,
    @NotNull
    String lastName,
    @Column(unique=true)
    String email,
    List<AppRole> roles
){
}
