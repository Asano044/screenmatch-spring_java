package br.com.casasbahia.screenmatch;

import br.com.casasbahia.screenmatch.model.ConversaoDados;
import br.com.casasbahia.screenmatch.model.DadosEpisodio;
import br.com.casasbahia.screenmatch.model.DadosSerie;
import br.com.casasbahia.screenmatch.model.DadosTemporada;
import br.com.casasbahia.screenmatch.principal.Principal;
import br.com.casasbahia.screenmatch.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception {
		System.out.println("Primeiro projeto Spring sem Web");

		Principal menu = new Principal();
		menu.exibeMenu();


	}
}
