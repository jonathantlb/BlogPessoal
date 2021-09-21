package com.BlogPessoal.projeto.generation.repositorios;

import com.BlogPessoal.projeto.generation.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    /**
     * Método utilizado para pesquisar coluna nome ContainigIgonreCase
     *
     * @param nome do tipo String
     * @return List de Usuarios
     * @author MrLucc
     * @since 1.0
     */

    List<Usuario> findAllByNomeContainingIgnoreCase(String nome);


    /**
     * Método utilizado para pesquisar coluna email
     *
     * @param email do tipo String
     * @return Optional com Usuario
     * @author MrLucc
     * @since 1.0
     */

    Optional<Usuario> findByEmail(String email);
}
