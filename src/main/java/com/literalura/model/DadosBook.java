package com.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosBook(@JsonAlias("title") String title,
                        @JsonAlias("authors") List<DadosAuthor> authors,
                        @JsonAlias("languages") List<String> languages,
                        @JsonAlias("download_count") String numeroDownloads
){
}