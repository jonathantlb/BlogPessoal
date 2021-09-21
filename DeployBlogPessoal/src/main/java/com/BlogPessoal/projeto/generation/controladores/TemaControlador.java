package com.BlogPessoal.projeto.generation.controladores;


import com.BlogPessoal.projeto.generation.modelos.Tema;
import com.BlogPessoal.projeto.generation.repositorios.TemaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tema")
public class TemaControlador {

    private @Autowired
    TemaRepositorio repositorio;

    @GetMapping("/todos")
    public ResponseEntity<List<Tema>> pegarTodos() {
        List<Tema> objetoLista = repositorio.findAll();

        if (objetoLista.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(objetoLista);
        }
    }

    @PostMapping("/salvar")
    public ResponseEntity<Tema> salvar(@Valid @RequestBody Tema novoTema) {
        return ResponseEntity.status(201).body(repositorio.save(novoTema));
    }

    @GetMapping("/id/{id_tema}")
    public ResponseEntity<Tema> buscarPorId(@PathVariable(value = "id_tema") Long idTema){
        Optional<Tema> objetoTema = repositorio.findById(idTema);

        if(objetoTema.isPresent()){
            return ResponseEntity.status(200).body(objetoTema.get());
        }else{
            return ResponseEntity.status(200).build();
        }
    }


    @GetMapping("/{tema}")
    public ResponseEntity<List<Tema>> buscarPorIdI(@PathVariable(value = "tema") String tema) {
        List<Tema> objetoLista = repositorio.findAllByTemaContainingIgnoreCase(tema);

        if (objetoLista.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(objetoLista);
        }
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<List<Tema>> buscarPorIdII(@RequestParam(defaultValue = "") String tema) {
        List<Tema> objetoTema = repositorio.findAllByTemaContainingIgnoreCase(tema);

        if (objetoTema.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(objetoTema);
        }
    }


    @PutMapping("/atualizar")
    public ResponseEntity<Tema> atualizar(@Valid @RequestBody Tema temaParaAtualizar) {
        return ResponseEntity.status(201).body(repositorio.save(temaParaAtualizar));
    }

    @DeleteMapping("/deleter/{id_tema}")
    public void deletarTemaPorId(@PathVariable(value = "id_tema") Long idTema) {
        repositorio.deleteById(idTema);
    }
}
