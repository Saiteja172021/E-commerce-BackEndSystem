package com.ecommerce.estore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data // Generates getters, setters, equals, hashCode, and toString
@NoArgsConstructor // No-argument constructor
@AllArgsConstructor // All-arguments constructor
@Builder // Builder pattern support
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private String role; // e.g., "ROLE_USER" or "ROLE_ADMIN"
}
