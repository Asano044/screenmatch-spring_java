package br.com.casasbahia.screenmatch;

import br.com.casasbahia.screenmatch.model.ConverteDados;
import br.com.casasbahia.screenmatch.model.DadosSerie;
import br.com.casasbahia.screenmatch.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Converter;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String...args) {
		ConsumoApi consumo = new ConsumoApi();
		ConverteDados conversor = new ConverteDados();
		DadosSerie dadosSerie;
		System.out.println("Primeiro projeto Spring sem Web");
		String json = consumo.obterDados("http://www.omdbapi.com/?t=doctor+who&apikey=a745f28e");
		System.out.println(json);
		dadosSerie = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dadosSerie);

	}
}
