package br.com.casasbahia.screenmatch.principal;

import br.com.casasbahia.screenmatch.model.ConverteDados;
import br.com.casasbahia.screenmatch.model.DadosEpisodio;
import br.com.casasbahia.screenmatch.model.DadosSerie;
import br.com.casasbahia.screenmatch.model.DadosTemporada;
import br.com.casasbahia.screenmatch.service.ConsumoApi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private DadosSerie dadosSerie;
    private DadosTemporada dadosTemporada;
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=a745f28e";

    public void exibeMenu() {
        System.out.println("Informe o nome da série: ");
        String nomeSerie = leitura.nextLine();
        String json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        dadosSerie = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

		List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&Season=" + i +
					API_KEY);
			temporadas.add(conversor.obterDados(json, DadosTemporada.class));
		}
		temporadas.forEach(System.out::println);

//        Exibindo apenas o nome dos episódios
//        temporadas.forEach(t -> t.episodio().forEach(e -> System.out.println(e.titulo())));

//        Colocanto todos episódios em uma lista à parte
        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                        .flatMap(t -> t.episodio().stream())
                                .toList();
//        Filtrando os 5 melhores episódios da série
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}
