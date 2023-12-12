package ru.abolodurin.taskmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Schema(description = "Username, uses as auth login")
    @NotEmpty(message = "Field username must be not empty")
    private String username;

    @Schema(description = "User email", example = "example@mail.com")
    @NotEmpty(message = "Field email must be not empty")
    @Email(message = "Please enter a valid email address")
    private String email;

    @Schema(description = "User password")
    @NotEmpty(message = "Field password must be not empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

}
