package com.BlogPessoal.projeto.generation.seguranca;

import com.BlogPessoal.projeto.generation.modelos.Usuario;
import com.BlogPessoal.projeto.generation.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

    private @Autowired
    UsuarioRepositorio repositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> objetoOptional = repositorio.findByEmail(username);

        if(objetoOptional.isPresent()){
            return new UserDetailsImplements(objetoOptional.get());

        }else{
            throw new UsernameNotFoundException(username + "Não existe");

        }
    }
}
