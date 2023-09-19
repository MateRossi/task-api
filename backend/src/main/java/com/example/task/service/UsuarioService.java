package com.example.task.service;

import com.example.task.model.entity.Usuario;
import com.example.task.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> getUsuario() { return repository.findAll(); }

    public Optional<Usuario> getUsuarioById(Long id) { return repository.findById(id); }

    @Transactional
    public Usuario save(@Valid Usuario usuario) {
        return repository.save(usuario);
    }

    @Transactional
    public void delete(Usuario usuario){
        Objects.requireNonNull(usuario.getId());
        repository.delete(usuario);
    }
}
