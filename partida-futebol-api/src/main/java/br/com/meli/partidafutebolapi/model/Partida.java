package br.com.meli.partidafutebolapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    private int goalsMandante;

    @Column(name="resultado_visitante")
    private int goalsVisitante;

    @Column(name="data_hora_partida")
    private LocalDateTime dataHoraPartida;

    @Column(name="estadio")
    @NotBlank
    private String nomeEstadio;
}
