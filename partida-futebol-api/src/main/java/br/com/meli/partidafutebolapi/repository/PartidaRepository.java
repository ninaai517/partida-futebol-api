package br.com.meli.partidafutebolapi.repository;

import br.com.meli.partidafutebolapi.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepository extends JpaRepository<Partida,Long> {
}
