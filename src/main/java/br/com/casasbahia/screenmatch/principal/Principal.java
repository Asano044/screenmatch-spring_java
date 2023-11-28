package br.com.casasbahia.screenmatch.principal;

import br.com.casasbahia.screenmatch.model.ConversaoDados;
import br.com.casasbahia.screenmatch.model.DadosSerie;
import br.com.casasbahia.screenmatch.model.DadosTemporada;
import br.com.casasbahia.screenmatch.service.ConsumoApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private ConsumoApi consumo = new ConsumoApi();
    private ConversaoDados conversor = new ConversaoDados();
    private DadosSerie dadosSerie;
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=a745f28e";

    public void exibeMenu() {
        System.out.println("Informe o nome da série: ");
        String serie = leitura.nextLine();

        String json = consumo.obterDados(
                ENDERECO +
                serie.replace(" ", "+") +
                API_KEY);
        dadosSerie = conversor.obterDados(json, DadosSerie.class);
        List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i < dadosSerie.totalTemporadas(); i++) {
			json = consumo.obterDados(ENDERECO +
                    serie.replace(" ", "+") +
                    "&Season=" + i +
                    API_KEY);
			temporadas.add(conversor.obterDados(json, DadosTemporada.class));
		}
		temporadas.forEach(System.out::println);


    }
}
