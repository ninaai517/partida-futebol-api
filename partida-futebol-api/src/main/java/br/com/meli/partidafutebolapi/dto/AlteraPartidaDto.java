package br.com.meli.partidafutebolapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlteraPartidaDto {
    private Long id;
    private String clubeMandante;
    private String clubeVisitante;
    private int goalsMandante;
    private int goalsVisitante;
    private String estadio;
    private LocalDateTime dataHoraPartida;
}
