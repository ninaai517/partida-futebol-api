package br.com.meli.partidafutebolapi.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AlteraPartidaDto {
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

}
