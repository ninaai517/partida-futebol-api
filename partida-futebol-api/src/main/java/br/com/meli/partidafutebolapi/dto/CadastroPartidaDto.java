package br.com.meli.partidafutebolapi.dto;

import br.com.meli.partidafutebolapi.model.Partida;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CadastroPartidaDto {
    private Long id;
    private String clubeMandante;
    private String clubeVisitante;
    private int goalsMandante;
    private int goalsVisitante;
    private String estadio;
    private LocalDateTime dataHoraPartida;

    public CadastroPartidaDto(){}
    public CadastroPartidaDto(Partida partida){
        this.id = partida.getId();
        this.clubeMandante = partida.getClubeMandante();
        this.clubeVisitante = partida.getClubeVisitante();
        this.goalsMandante = partida.getGoalsMandante();
        this.goalsVisitante = partida.getGoalsVisitante();
        this.dataHoraPartida = partida.getDataHoraPartida();
        this.estadio = partida.getNomeEstadio();
    }
}
