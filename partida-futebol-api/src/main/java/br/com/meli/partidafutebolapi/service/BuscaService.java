package br.com.meli.partidafutebolapi.service;

import br.com.meli.partidafutebolapi.dto.PartidaDto;
import br.com.meli.partidafutebolapi.model.Partida;
import br.com.meli.partidafutebolapi.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BuscaService {

    @Autowired
    public PartidaRepository repository;

    public List<PartidaDto> getAllEstadios(String estadio){
        List<Partida> partidas = repository.findAllByNomeEstadioEqualsIgnoreCase(estadio);

        return convertToListPartidas(partidas);

    }

    public List<Partida> getAllZeroGoals() {
        return repository.findAll().stream().filter(empates ->
                empates.getGoalsMandante() - empates.getGoalsVisitante() == 0
        ).collect(Collectors.toList());
    }

    private List<PartidaDto> convertToListPartidas(List<Partida> partidas) {
        return partidas.stream().map(PartidaDto::new).collect(Collectors.toList());
    }

}
