package br.com.meli.partidafutebolapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.ScriptAssert;

import java.time.LocalDateTime;

@Entity
@Table(name="tb_partida")
@Getter
@Setter
@Data

public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_partida")
    private Long id;

    @Column(name = "clube_mandante")
    private String clubeMandante;

    @Column(name = "clube_visitante")
    private String clubeVisitante;

    @Column(name="resultado_mandante")
    private Integer goalsMandante;

    @Column(name="resultado_visitante")
    private Integer goalsVisitante;

    @Column(name="data_hora_partida")
    private LocalDateTime dataHoraPartida;

    @Column(name="estadio")
    private String nomeEstadio;




}
