package br.com.meli.partidafutebolapi.repository;

import br.com.meli.partidafutebolapi.model.PartidaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepository extends JpaRepository<PartidaModel,Long> {


}
