package com.BlogPessoal.projeto.generation.modelos;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//ANOTAÇÃO QUE A CLASS SERA UM TEST DA CALSSE PRINCIÁL, WEBENVIRNONMENT.RANDOM_PORT,
// BASICAMENTE UMA ANOTAÇÃO CASO PORTA PADRÃO ESTEJA SENDO UTILIZADO, POSSA PROCURAR OUTRA.

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioTest {
    public Usuario usuario;
    public Usuario usuarioNulo = new Usuario();

    @Autowired
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    Validator validator = factory.getValidator();

    @BeforeEach
    public void start() {
        usuario = new Usuario(1L, "João da Silva", "joao@email.com", "12345678");
    }


}
