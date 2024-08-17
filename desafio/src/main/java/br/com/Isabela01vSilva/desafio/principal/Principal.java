package br.com.Isabela01vSilva.desafio.principal;

import br.com.Isabela01vSilva.desafio.service.ConsumoApi;

import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {
        System.out.println("Menu de opções:");
        System.out.println("Carro");
        System.out.println("Moto");
        System.out.println("Caminhão");
        var opcao = scanner.nextLine();
        String endereco;

        if (opcao.toUpperCase().contains("carro")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toUpperCase().contains("moto")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);
    }
}
