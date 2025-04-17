package com.fiap.SmartWatt.security.app.restmodel;

import com.fiap.SmartWatt.security.app.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}
