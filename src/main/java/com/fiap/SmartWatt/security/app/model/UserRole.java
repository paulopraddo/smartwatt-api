package com.fiap.SmartWatt.security.app.model;

import com.fiap.SmartWatt.security.app.exceptions.InvalidUserDataException;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public static UserRole fromString(String value) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.getRole().equalsIgnoreCase(value)) {
                return userRole;
            }
        }
        throw new InvalidUserDataException("Perfil inválido: " + value);
    }
}
