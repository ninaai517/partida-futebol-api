package br.com.meli.partidafutebolapi.service;

import br.com.meli.partidafutebolapi.dto.PartidaDto;
import br.com.meli.partidafutebolapi.model.Partida;
import br.com.meli.partidafutebolapi.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<PartidaDto> getAllPartidasByTeam(String clube, String filtro){

        switch (filtro){
            case "allGames":
                List<Partida> todasPartidas = repository.findAllByClubeMandanteOrClubeVisitante(clube, clube);
                return convertToListPartidas(todasPartidas);

            case "mandante":
                List<Partida> mandante = repository.findAllByClubeMandante(clube);
                return convertToListPartidas(mandante);

            case "visitante":
                List<Partida> visitante = repository.findAllByClubeVisitante(clube);
                return convertToListPartidas(visitante);

            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
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
