package ru.abolodurin.taskmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Schema(description = "User username")
    @NotEmpty(message = "Field username must be not empty")
    private String username;

    @Schema(description = "User password")
    @NotEmpty(message = "Field password must be not empty")
    private String password;

}
