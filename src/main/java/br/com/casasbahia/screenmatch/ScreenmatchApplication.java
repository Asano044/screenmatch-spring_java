package br.com.casasbahia.screenmatch;

import br.com.casasbahia.screenmatch.model.DadosSerie;
import br.com.casasbahia.screenmatch.model.DadosTemporada;
import br.com.casasbahia.screenmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String...args) {
		Principal principal = new Principal();
		principal.exibeMenu();
//		ConsumoApi consumo = new ConsumoApi();
//		ConverteDados conversor = new ConverteDados();
//		DadosSerie dadosSerie;
//		DadosTemporada dadosTemporada;
//		DadosEpisodio dadosEpisodio;
//		System.out.println("Primeiro projeto Spring sem Web");
//		String json = consumo.obterDados("http://www.omdbapi.com/?t=doctor+who&apikey=a745f28e");
//
//		dadosSerie = conversor.obterDados(json, DadosSerie.class);
//		System.out.println(dadosSerie);
//
//		json = consumo.obterDados("https://www.omdbapi.com/?t=doctor+who&Season=1&Episode=1&apikey=a745f28e");
//		dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
//		System.out.println(dadosEpisodio);
//
//		List<DadosTemporada> temporadas = new ArrayList<>();
//		for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
//			json = consumo.obterDados("https://www.omdbapi.com/?t=doctor+who&Season=" + i +
//					"&apikey=a745f28e");
//			temporadas.add(conversor.obterDados(json, DadosTemporada.class));
//		}
//		temporadas.forEach(System.out::println);
//	}
//}
