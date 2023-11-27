package br.com.casasbahia.screenmatch;

import br.com.casasbahia.screenmatch.model.ConversaoDados;
import br.com.casasbahia.screenmatch.model.DadosEpisodio;
import br.com.casasbahia.screenmatch.model.DadosSerie;
import br.com.casasbahia.screenmatch.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception {
		System.out.println("Primeiro projeto Spring sem Web");

		ConsumoApi consumo = new ConsumoApi();
		String doctor = consumo.obterDados("https://www.omdbapi.com/?t=doctor+who&apikey=a745f28e");
		System.out.println(doctor);

		ConversaoDados conversor = new ConversaoDados();
		var novoDoutor = conversor.obterDados(doctor, DadosSerie.class);
		System.out.println(novoDoutor);

		String episodioDoutor = consumo.obterDados("https://www.omdbapi.com/?t=doctor+who&Season=1&Episode=1&apikey=a745f28e");
		var episodioRose = conversor.obterDados(episodioDoutor, DadosEpisodio.class);
		System.out.println(episodioRose);
	}
}
