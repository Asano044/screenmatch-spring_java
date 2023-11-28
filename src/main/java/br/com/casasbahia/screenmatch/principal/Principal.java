package br.com.casasbahia.screenmatch.principal;

import br.com.casasbahia.screenmatch.service.ConsumoApi;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=a745f28e";

    public String exibeMenu() {
        System.out.println("Informe o nome da série: ");
        String serie = leitura.nextLine();
        ConsumoApi consumo = new ConsumoApi();
        String json = consumo.obterDados(
                ENDERECO +
                serie.replace(" ", "+") +
                API_KEY);
        return json;

    }
}
