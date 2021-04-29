package com.mau.spring.controller;

import com.mau.spring.model.LoginDTO;
import com.mau.spring.model.TokenDTO;
import com.mau.spring.model.UsuarioWeb;
import com.mau.spring.model.UsuarioWebDTO;
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
    TokenDTO register(@RequestBody UsuarioWebDTO body) {
        System.out.println(body);

        users.guardar(new UsuarioWeb(body.getEmail(),body.getNombre(),body.getApellido(),body.getEmail(),body.getPassword()));
        return login(new LoginDTO(body.getEmail(), body.getPassword()));
    }

    @PostMapping("/login")
    TokenDTO login(@RequestBody LoginDTO body) {

        TokenDTO respuesta = new TokenDTO(authentication
                .login(body.getEmail(), body.getPassword())
                .orElseThrow(() -> new RuntimeException("Email o contraseña inválidos")));
        return respuesta;
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

