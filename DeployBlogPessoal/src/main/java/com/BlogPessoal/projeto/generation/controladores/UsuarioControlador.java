package com.BlogPessoal.projeto.generation.controladores;


import com.BlogPessoal.projeto.generation.modelos.Usuario;
import com.BlogPessoal.projeto.generation.modelos.utilidades.UsuarioDTO;
import com.BlogPessoal.projeto.generation.repositorios.UsuarioRepositorio;
import com.BlogPessoal.projeto.generation.servicos.UsuarioServicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    private @Autowired
    UsuarioRepositorio repositorio;
    private @Autowired
    UsuarioServicos servicos;

    @GetMapping("/todes")
    public ResponseEntity<List<Usuario>> pegarTodes() {
        List<Usuario> objetoLista = repositorio.findAll();

        if (objetoLista.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(objetoLista);
        }

    }

    @PostMapping("/salvar")
    public ResponseEntity<Object> salvar(@Valid @RequestBody Usuario novoUsuario) {
        Optional<Object> objetoOptional = servicos.cadastrarUsuario(novoUsuario);

        if (objetoOptional.isEmpty()) {
            return ResponseEntity.status(400).build();
        } else {
            return ResponseEntity.status(201).body(objetoOptional.get());
        }
    }

    @PutMapping("/credenciais")
    public ResponseEntity<Object> credenciais(@Valid @RequestBody UsuarioDTO usuarioParaAutenticar) {
        Optional<?> objetoOptional = servicos.pegarCredenciais(usuarioParaAutenticar);

        if (objetoOptional.isEmpty()) {
            return ResponseEntity.status(400).build();
        } else {
            return ResponseEntity.status(201).body(objetoOptional.get());
        }
    }

    @GetMapping("/{id_usuario}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
        Optional<Usuario> objetoUsuario = repositorio.findById(idUsuario);

        if (objetoUsuario.isPresent()) {
            return ResponseEntity.status(200).body(objetoUsuario.get());
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    @GetMapping("/nome/{nome_usuario}")
    public ResponseEntity<List<Usuario>> buscarPorNomeI(@PathVariable(value = "nome_usuario") String nome) {
        List<Usuario> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);

        if (objetoLista.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(objetoLista);
        }
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<List<Usuario>> buscarPorNomeII(@RequestParam(defaultValue = "") String nome) {
        List<Usuario> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);

        if (objetoLista.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(objetoLista);
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario usuarioParaAtualizar) {
        return ResponseEntity.status(201).body(repositorio.save(usuarioParaAtualizar));
    }

    @DeleteMapping("/deletar/{id_usuario}")
    public void deletarUsuarioPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
        repositorio.deleteById(idUsuario);
    }
}
