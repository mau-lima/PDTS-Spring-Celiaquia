package com.mau.spring.service;

import com.mau.spring.model.UsuarioWeb;

import java.util.Optional;


/**
 * Interfaz responsable de Alta y consulta (proxiammente Baja y modificación) de Usuarios Web
 */
public interface UsuarioWebService {

        UsuarioWeb guardar(UsuarioWeb user);

        Optional<UsuarioWeb> buscar(String id);

        Optional<UsuarioWeb> buscarPorEmail(String email);
}
