package br.com.casasbahia.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer episodio;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodio(String numero, DadosEpisodio dadosEpisodio) {
        this.temporada = Integer.valueOf(numero);
        this.titulo = dadosEpisodio.titulo();
        this.episodio = dadosEpisodio.episodio();
        try {
            this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());
        } catch (NumberFormatException ex) {
            this.avaliacao = 0.0;
        } try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        } catch (DateTimeParseException ex) {
            this.dataLancamento = null;
        }
    }

    public Integer getTemporada() {
        return temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getEpisodio() {
        return episodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    @Override
    public String toString() {
        return "(" +
                "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", episodio=" + episodio +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento +
                ')';
    }
}
