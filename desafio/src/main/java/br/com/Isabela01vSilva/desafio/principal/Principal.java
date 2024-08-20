package br.com.Isabela01vSilva.desafio.principal;

import br.com.Isabela01vSilva.desafio.model.Dados;
import br.com.Isabela01vSilva.desafio.model.Modelos;
import br.com.Isabela01vSilva.desafio.service.ConsumoApi;
import br.com.Isabela01vSilva.desafio.service.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {
        System.out.println("Menu de opções:");
        System.out.println("Carro");
        System.out.println("Moto");
        System.out.println("Caminhão");
        var opcao = scanner.nextLine();
        String endereco;

        if (opcao.toLowerCase().contains("carro")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("moto")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);
        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta:");
        var codigoMarca = scanner.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumo.obterDados(endereco);
        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca:");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);
    }
}