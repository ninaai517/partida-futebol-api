package br.com.meli.partidafutebolapi.dto;

import br.com.meli.partidafutebolapi.model.Partida;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class PartidaDto {
    private Long id;

    @NotBlank
    private String clubeMandante;

    @NotBlank
    private String clubeVisitante;

    @NotNull
    @PositiveOrZero
    private Integer goalsMandante;

    @NotNull
    @PositiveOrZero
    private Integer goalsVisitante;

    @NotBlank
    private String estadio;

    @PastOrPresent
    @NotNull
    private LocalDateTime dataHoraPartida;

    public PartidaDto(){}
    public PartidaDto(Partida partida){
        this.id = partida.getId();
        this.clubeMandante = partida.getClubeMandante();
        this.clubeVisitante = partida.getClubeVisitante();
        this.goalsMandante = partida.getGoalsMandante();
        this.goalsVisitante = partida.getGoalsVisitante();
        this.dataHoraPartida = partida.getDataHoraPartida();
        this.estadio = partida.getNomeEstadio();
    }

}
