package com.mau.spring.auth;

import com.google.common.collect.ImmutableMap;
import com.mau.spring.model.UsuarioWeb;
import com.mau.spring.service.UsuarioWebAuthService;
import com.mau.spring.service.UsuarioWebService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
final class TokenAuthenticationService implements UsuarioWebAuthService {
    @NonNull
    TokenService tokens;
    @NonNull
    UsuarioWebService users;

    @Override
    public Optional<String> login(final String email, final String password) {
        return users
                .buscarPorEmail(email)
                .filter(user -> Objects.equals(password, user.getPassword()))
                .map(user -> tokens.expiring(ImmutableMap.of("email", email)));
    }

    @Override
    public Optional<UsuarioWeb> findByToken(final String token) {
        return Optional
                .of(tokens.verify(token))
                .map(map -> map.get("email"))
                .flatMap(users::buscarPorEmail);
    }

    @Override
    public void logout(final UsuarioWeb user) {
        // Nothing to doy
    }
}