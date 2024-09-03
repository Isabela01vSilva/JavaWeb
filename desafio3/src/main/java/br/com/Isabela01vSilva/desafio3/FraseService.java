package br.com.Isabela01vSilva.desafio3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FraseService {

    @Autowired
    private FraseRepository repositorio;

    public FraseDTO obterFraseAleatoria() {
        Frase frase = repositorio.buscaFraseAleatoria();
        return new FraseDTO(frase.getNomeSerie(), frase.getFrase(), frase.getPersonagem(), frase.getPoster());
    }
}
