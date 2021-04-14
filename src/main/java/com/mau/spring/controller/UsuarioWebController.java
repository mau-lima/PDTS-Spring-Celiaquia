package com.mau.spring.controller;

import com.mau.spring.model.UsuarioWeb;
import com.mau.spring.service.UsuarioWebAuthService;
import com.mau.spring.service.UsuarioWebService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/usuarioWeb")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
final class UsuarioWebController {
    @NonNull
    UsuarioWebAuthService authentication;
    @NonNull
    UsuarioWebService users;

    @PostMapping("/register")
    String register(
            @RequestParam("nombre") final String nombre,
            @RequestParam("apellido") final String apellido,
            @RequestParam("email") final String email,
            @RequestParam("password") final String password
            ) {
        users
                .guardar(
                      new UsuarioWeb(email,nombre,apellido,email,password)
                );

        return login(email, password);
    }

    @PostMapping("/login")
    String login(
            @RequestParam("email") final String email,
            @RequestParam("password") final String password) {
        return authentication
                .login(email, password)
                .orElseThrow(() -> new RuntimeException("Email o contraseña inválidos"));
    }


    @GetMapping("/current")
    UsuarioWeb getCurrent(@AuthenticationPrincipal final UsuarioWeb user) {
        return user;
    }

    @GetMapping("/logout")
    boolean logout(@AuthenticationPrincipal final UsuarioWeb user) {
        authentication.logout(user);
        return true;
    }
}

