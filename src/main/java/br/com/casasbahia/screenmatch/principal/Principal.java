package br.com.casasbahia.screenmatch.principal;

import br.com.casasbahia.screenmatch.model.*;
import br.com.casasbahia.screenmatch.service.ConsumoApi;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Principal {
    private ConsumoApi consumo = new ConsumoApi();
    private ConversaoDados conversor = new ConversaoDados();
    private DadosSerie dadosSerie;
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=a745f28e";
    private Integer anoResposta;

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

//        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
//                .flatMap(t -> t.episodios().stream())
//                .toList();


        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());
        episodios.forEach(System.out::println);

//        FILTRO DE 5 MELHORES EPISÓDIOS
//        System.out.println("\nOs 5 melhores episódios dessa série: ");
//        episodios.stream()
//                .filter(e -> !e.getAvaliacao().equals("N/A"))
//                .sorted(Comparator.comparing(Episodio::getAvaliacao).reversed())
//                .limit(5)
//                .forEach(System.out::println);


//        -- FILTRO DE EPISÓDIOS POR TITULO
//        System.out.println("Digite um trecho do titulo do episódio: ");
//        String trechoTexto = leitura.nextLine();
//        Optional<Episodio> episodioBuscado =  episodios.stream()
//                .filter(e -> e.getTitulo().toLowerCase().contains(trechoTexto.toLowerCase()))
//                .findFirst();
//        System.out.println(episodioBuscado);
//        if (episodioBuscado.isPresent()) {
//            System.out.println("Episódio encontrado");
//            System.out.println(episodioBuscado.get());
//        } else {
//            System.out.println("Episódio não encontrado!");
//    }

//        System.out.println("A partir de qual ano você quer ver os episódios da série? ");
//        this.anoResposta = leitura.nextInt();
//        leitura.nextLine();
//
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream()
//                .filter(e -> e.getDataLancamento().getYear() >= anoResposta)
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                " Episódio: " + e.getEpisodio() +
//                                " Titulo: " + e.getTitulo() +
//                                " - Data Lançamento: " + e.getDataLancamento().format(formatador)
//                ));
//
//        CRIANDO A MÉDIA DAS AVALIAÇÕES
        Map<Integer, Double> avaliacaoPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada, Collectors.averagingDouble(Episodio::getAvaliacao)));
        System.out.println(avaliacaoPorTemporada);
    }
}