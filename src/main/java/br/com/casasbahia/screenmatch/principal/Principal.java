package br.com.casasbahia.screenmatch.principal;

import br.com.casasbahia.screenmatch.model.*;
import br.com.casasbahia.screenmatch.service.ConsumoApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
//        dadosEpisodios.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .limit(5)
//                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodio().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).toList();

//        Filtro para exibir o episódio informado
        System.out.println("Informe um trecho do episódio: ");
        String resposta = leitura.nextLine();

        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(resposta.toUpperCase()))
                .findFirst();
        if (episodioBuscado.isPresent()) {
            System.out.println("Episódio encontrado!");
            System.out.println(
                    "Temporada: " + episodioBuscado.get().getTemporada() +
                            ", Episódio: " + episodioBuscado.get().getTitulo()
            );
        } else {
            System.out.println("Episódio não encontrado!");
        }

//        Filtrando os 5 melhores episódios - adaptado
//        System.out.println("\nOs 5 melhores episódios\n");
//        episodios.stream()
//                .filter(e -> e.getAvaliacao()>0.0)
//                .sorted(Comparator.comparing(Episodio::getAvaliacao).reversed())
//                .limit(5)
//                .forEach(System.out::println);

//        Filtrando os episódios exibidos com base no ano informado
//        System.out.println("Deseja ver os episódios lançados a partir de qual ano: ");
//        int anoResposta = leitura.nextInt();
//        leitura.nextLine();
//
//        LocalDate dataFiltro = LocalDate.of(anoResposta, 1, 1);
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataFiltro))
//                .forEach(e -> System.out.println(
//                        "( Temporada: " + e.getTemporada() +
//                        ", Episódio: " + e.getTitulo() +
//                        " - Data de Lançamento: " + e.getDataLancamento().format(formatador) + " )"
//                ));
    }
}
