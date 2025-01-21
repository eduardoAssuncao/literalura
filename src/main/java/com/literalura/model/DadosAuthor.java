package com.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAuthor(@JsonAlias("name") String name,
                          @JsonAlias("birth_year") Integer anoNascimento,
                          @JsonAlias("death_year") Integer anoFalecimento
){
}
