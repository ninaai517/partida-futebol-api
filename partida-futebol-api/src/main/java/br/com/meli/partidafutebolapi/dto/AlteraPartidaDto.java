package br.com.meli.partidafutebolapi.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

    @NotBlank
    private String estadio;

    @PastOrPresent
    private LocalDateTime dataHoraPartida;

    public Boolean validaHora(){
        Boolean verificaHr = this.dataHoraPartida.isAfter(LocalDateTime.now());

        if(verificaHr){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return true;
    }
}
