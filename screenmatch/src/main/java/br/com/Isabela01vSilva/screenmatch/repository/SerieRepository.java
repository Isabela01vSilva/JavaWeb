package br.com.Isabela01vSilva.screenmatch.repository;

import br.com.Isabela01vSilva.screenmatch.model.Categoria;
import br.com.Isabela01vSilva.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int temporadasMaxima, double avaliacao);

    @Query("select s from Serie s where s.totalTemporadas <= :temporadasMaxima and s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(int temporadasMaxima, double avaliacao);

}
