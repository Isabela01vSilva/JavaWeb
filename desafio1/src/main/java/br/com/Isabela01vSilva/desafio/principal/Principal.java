package br.com.Isabela01vSilva.desafio.principal;

import br.com.Isabela01vSilva.desafio.model.Dados;
import br.com.Isabela01vSilva.desafio.model.Modelos;
import br.com.Isabela01vSilva.desafio.model.Veiculo;
import br.com.Isabela01vSilva.desafio.service.ConsumoApi;
import br.com.Isabela01vSilva.desafio.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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

        AtomicReference<String> json = new AtomicReference<>(consumo.obterDados(endereco));
        System.out.println(json.get());
        List<Dados> marcas = conversor.obterLista(json.get(), Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta:");
        String codigoMarca = scanner.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json.set(consumo.obterDados(endereco));
        Modelos modeloLista = conversor.obterDados(json.get(), Modelos.class);

        System.out.println("\nModelos dessa marca:");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado");
        String nomeVeiculo = scanner.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos Filtrados:");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o código do modelo, para buscar os valores");
        var codigoModelo = scanner.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json.set(consumo.obterDados(endereco));
        List<Dados> anos = conversor.obterLista(json.get(), Dados.class);


        List<Veiculo> veiculos = new ArrayList<>();

//        for (int i = 0; i < anos.size(); i++) {
//            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
//            json = consumo.obterDados(enderecoAnos);
//            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
//            veiculos.add(veiculo);
//        }

        String finalEndereco = endereco;
        anos.stream()
                .forEach(ano -> {
                    var enderecoAnos = finalEndereco + "/" + ano.codigo();
                    json.set(consumo.obterDados(enderecoAnos));
                    Veiculo veiculo = conversor.obterDados(json.get(), Veiculo.class);
                    veiculos.add(veiculo);
                });

        System.out.println("\nTodos os veiculos filtrados");
        veiculos.forEach(System.out::println);

    }
}
