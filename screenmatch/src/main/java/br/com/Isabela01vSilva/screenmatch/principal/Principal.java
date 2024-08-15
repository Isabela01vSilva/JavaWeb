package br.com.Isabela01vSilva.screenmatch.principal;

import br.com.Isabela01vSilva.screenmatch.service.ConsumoApi;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();

    private final String ENDERECO = "https://omdbapi.com/?t=";
    private final String APIKEY = "&apikey=6585022c";

    public  void exibeMenu() {
        System.out.println("Digite o nome da série: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);

    }
}
