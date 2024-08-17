package br.com.Isabela01vSilva.desafio.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
