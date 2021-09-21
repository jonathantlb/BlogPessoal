package com.BlogPessoal.projeto.generation.servicos;


import com.BlogPessoal.projeto.generation.modelos.Usuario;
import com.BlogPessoal.projeto.generation.modelos.utilidades.UsuarioDTO;
import com.BlogPessoal.projeto.generation.repositorios.UsuarioRepositorio;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UsuarioServicos {

    private @Autowired
    UsuarioRepositorio repositorio;

    /**
     * Métedo utilizado para criação de um novo usuario no sistema e criptografia da senha
     *
     * @param novoUsuario do tipo Usuario
     * @return Optional com Usuario Criado
     * @author MrLucc
     * @since 1.5
     *
     */

    public Optional<Object> cadastrarUsuario(Usuario novoUsuario){
        return repositorio.findByEmail(novoUsuario.getEmail()).map(usuarioExistente -> {
            return Optional.empty();
        }).orElseGet(() -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String result = encoder.encode(novoUsuario.getSenha());
            novoUsuario.setSenha(result);
            return Optional.ofNullable(repositorio.save(novoUsuario));
        });
    }

    /**
     * Método utilizado para pegar credenciais do usuario com Token (Formato basic),
     * este método sera utilizado para retonar ao front o token utilizado para ter acesso,
     * aos dados do usuario e mantelo logado no sistema
     *
     * @param usuarioParaAutenticar tipo UsuarioLoginDTO necessario email e senha,
     *                             para validar
     * @return UsuarioLoginDTO preechido com informações mais o token.
     * @since 1.0
     * @author MrLucc
     */

    public Optional<?> pegarCredenciais(UsuarioDTO usuarioParaAutenticar) {
        return repositorio.findByEmail(usuarioParaAutenticar.getEmail()).map(usuarioExistente -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if(encoder.matches(usuarioParaAutenticar.getSenha(), usuarioExistente.getSenha())) {
                String estruturaBasic = usuarioParaAutenticar.getEmail() + ":" + usuarioParaAutenticar.getSenha(); // email : senha
                byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII"))); // criptografia da senha
                String autorizacaoHeader = "Basic " + new String(autorizacaoBase64); // basic criptografia da senha

                usuarioParaAutenticar.setToken(autorizacaoHeader);
                usuarioParaAutenticar.setId(usuarioExistente.getIdUsuario());
                usuarioParaAutenticar.setNome(usuarioExistente.getNome());
                usuarioParaAutenticar.setSenha(usuarioExistente.getSenha());
                return Optional.ofNullable(usuarioParaAutenticar); // Usuario Credenciado

            }else{
                return Optional.ofNullable(usuarioParaAutenticar); // Senha esteja incorreta
            }

        }).orElseGet(() -> {
            return Optional.empty();
        });
    }
}
