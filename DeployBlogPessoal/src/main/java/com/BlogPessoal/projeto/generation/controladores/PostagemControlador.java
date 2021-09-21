package com.BlogPessoal.projeto.generation.controladores;


import com.BlogPessoal.projeto.generation.modelos.Postagem;
import com.BlogPessoal.projeto.generation.repositorios.PostagemRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagem")
public class PostagemControlador {

    private @Autowired
    PostagemRepositorio repositorio;

    @GetMapping("/todes")
    public ResponseEntity<List<Postagem>> pegarTodos(){
        List<Postagem> objetoLista = repositorio.findAll();

        if(objetoLista.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(200).body(objetoLista);
        }
    }

    @GetMapping("/{id_postagem}")
    public ResponseEntity<Postagem> buscarPorId(@PathVariable(value = "id_postagem") Long idPostagem){
        Optional<Postagem> objetoPostagem = repositorio.findById(idPostagem);

        if(objetoPostagem.isPresent()){
            return ResponseEntity.status(200).body(objetoPostagem.get());
        }else{
            return ResponseEntity.status(200).build();
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> buscarPorTituloI(@PathVariable(value = "titulo") String titulo) {
        List<Postagem> objetoLista = repositorio.findAllByTituloContainingIgnoreCase(titulo);

        if(objetoLista.isEmpty()) {
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(200).body(objetoLista);
        }
    }

//    @GetMapping("/pesquisa")
//    public ResponseEntity<List<Postagem>> buscarPorTituloII(@RequestParam(defaultValue = "") String titulo) {
//        List<Postagem> objetoLista = repositorio.findAllByTituloContainingIgnoreCase(titulo);
//
//        if(objetoLista.isEmpty()) {
//            return ResponseEntity.status(204).build();
//        }else{
//            return ResponseEntity.status(200).body(objetoLista);
//        }
//    }

    @PutMapping("/atualizar")
    public ResponseEntity<Postagem> atualizar(@Valid @RequestBody Postagem postagemParaAtualizar) {
        return ResponseEntity.status(201).body(repositorio.save(postagemParaAtualizar));
    }

    @PostMapping("/salvar")
    public ResponseEntity<Postagem> salvar(@Valid @RequestBody Postagem salvarPostagens) {
        return ResponseEntity.status(201).body(repositorio.save(salvarPostagens));
    }

    @DeleteMapping("/deletar/{id_postagem}")
    public void deletarPostagemPorId(@PathVariable(value = "id_postagem") Long idPostagem) {
        repositorio.deleteById(idPostagem);
    }
}
