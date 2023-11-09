package br.com.meli.partidafutebolapi.service;

import br.com.meli.partidafutebolapi.dto.PartidaDto;
import br.com.meli.partidafutebolapi.model.Partida;
import br.com.meli.partidafutebolapi.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscaService {

    @Autowired
    public PartidaRepository repository;

    public List<PartidaDto> getAllEstadios(String estadio){
        List<Partida> partidas = repository.findAllByNomeEstadioEqualsIgnoreCase(estadio);

        return convertToListPartidasPorEstadio(partidas);

    }

    private List<PartidaDto> convertToListPartidasPorEstadio(List<Partida> partidas) {
        return partidas.stream().map(PartidaDto::new).collect(Collectors.toList());
    }
}
