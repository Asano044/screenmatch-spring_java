package br.com.casasbahia.screenmatch.principal;

import br.com.casasbahia.screenmatch.model.*;
import br.com.casasbahia.screenmatch.service.ConsumoApi;

import java.util.*;
import java.util.stream.Collectors;

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

//        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .toList();

        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                    .map(d -> new Episodio(t.numero(), d))
                        ).collect(Collectors.toList());
        episodios.forEach(System.out::println);
    }
}
