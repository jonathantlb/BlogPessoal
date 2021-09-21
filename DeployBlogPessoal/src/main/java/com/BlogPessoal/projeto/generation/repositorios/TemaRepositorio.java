package com.BlogPessoal.projeto.generation.repositorios;

import com.BlogPessoal.projeto.generation.modelos.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemaRepositorio extends JpaRepository<Tema, Long> {

    /**
     * Método utilizado para pesquisar coluna tema ContainigIgnoreCase
     *
     * @param nome do tipo String
     * @return List de Temas
     * @author MrLucc
     */

    List<Tema> findAllByTemaContainingIgnoreCase(String tema);
}
