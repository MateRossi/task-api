package com.example.task.api.controller;

import com.example.task.api.dto.UsuarioDto;
import com.example.task.model.entity.Usuario;
import com.example.task.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping()
    public ResponseEntity get(){
        List<Usuario> usuarios = service.getUsuario();
        return ResponseEntity.ok(usuarios.stream().map(UsuarioDto::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = service.getUsuarioById(id);

        if(!usuario.isPresent()){
            return new ResponseEntity("Usuario not found!", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(usuario.map(UsuarioDto::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody UsuarioDto dto){
        try {
            Usuario usuario = converter(dto);
            usuario = service.save(usuario);
            return new ResponseEntity(usuario, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody UsuarioDto dto){
        if(!service.getUsuarioById(id).isPresent()){
            return new ResponseEntity("Usuario not found", HttpStatus.NOT_FOUND);
        }
        try {
            Usuario usuario = converter(dto);
            usuario.setId(id);
            service.save(usuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        Optional<Usuario> usuario = service.getUsuarioById(id);
        if(!usuario.isPresent()){
            return new ResponseEntity("Usuario not found", HttpStatus.NOT_FOUND);
        }
        try {
            service.delete(usuario.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Usuario converter(UsuarioDto dto){
        ModelMapper modelMapper = new ModelMapper();
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        return usuario;
    }
}
