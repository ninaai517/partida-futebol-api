package br.com.meli.partidafutebolapi.repository;

import br.com.meli.partidafutebolapi.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida,Long> {

    List<Partida> findAllByNomeEstadioEqualsIgnoreCase(String estadio);
    List<Partida> findAllByClubeMandante(String clube);
    List<Partida> findAllByClubeVisitante(String clube);
    List<Partida> findAllByClubeMandanteOrClubeVisitante(String clubeMandante, String clubeVisitante);
    List<Partida> findAllByDataHoraPartida(LocalDateTime dataHora);
}
