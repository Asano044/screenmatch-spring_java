package br.com.casasbahia.screenmatch.model;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
