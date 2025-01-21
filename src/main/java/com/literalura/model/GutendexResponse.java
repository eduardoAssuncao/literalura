package com.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexResponse(
    @JsonAlias("count") Integer count,
    @JsonAlias("next") String next,
    @JsonAlias("previous") String previous,
    @JsonAlias("results") List<DadosBook> results
) {}
