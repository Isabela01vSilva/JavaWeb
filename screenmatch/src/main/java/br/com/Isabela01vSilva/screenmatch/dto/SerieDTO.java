package br.com.Isabela01vSilva.screenmatch.dto;

import br.com.Isabela01vSilva.screenmatch.model.Categoria;

public record SerieDTO(
        Long id,
        String titulo,
        Integer totalTemporadas,
        Double avaliacao,
        Categoria genero,
        String atores,
        String poster,
        String sinopse
        ) {
}
