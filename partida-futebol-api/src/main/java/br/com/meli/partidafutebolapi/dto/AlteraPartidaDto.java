package br.com.meli.partidafutebolapi.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AlteraPartidaDto {
    private Long id;

    private String clubeMandante;

    private String clubeVisitante;

    @NotNull
    @PositiveOrZero
    private Integer goalsMandante;

    @NotNull
    @PositiveOrZero
    private Integer goalsVisitante;

    private String estadio;

    @PastOrPresent
    private LocalDateTime dataHoraPartida;

}
