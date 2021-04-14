package com.mau.spring.service;

import com.mau.spring.model.UsuarioWeb;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;


@Service
public class UsuarioWebEnMemoriaService implements UsuarioWebService {
    Map<String, UsuarioWeb> users = new HashMap<>();

    @Override
    public UsuarioWeb guardar(UsuarioWeb user) {
        return users.put(user.getId(), user);
    }

    @Override
    public Optional<UsuarioWeb> buscar(final String id) {
        return ofNullable(users.get(id));
    }

    @Override
    public Optional<UsuarioWeb> buscarPorEmail(String email) {
        return users
                .values()
                .stream()
                .filter(u -> Objects.equals(email, u.getEmail()))
                .findFirst();
    }
}

