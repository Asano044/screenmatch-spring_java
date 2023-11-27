package br.com.casasbahia.screenmatch.model;

public interface IConversaoDados {
    <T> T obterDados(String json, Class<T> classe);
}
