package br.com.casasbahia.screenmatch;

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
	public void run(String...args) {
		System.out.println("Primeiro projeto Spring sem Web");
		ConsumoApi consumo = new ConsumoApi();
		String json = consumo.obterDados("http://www.omdbapi.com/?t=doctor+who&Season=1&Episode=1&apikey=a745f28e");
		System.out.println(json);
	}
}
