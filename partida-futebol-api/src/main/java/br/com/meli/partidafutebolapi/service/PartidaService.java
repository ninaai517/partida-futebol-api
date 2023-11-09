package br.com.meli.partidafutebolapi.service;

import br.com.meli.partidafutebolapi.dto.AlteraPartidaDto;
import br.com.meli.partidafutebolapi.dto.CadastroPartidaDto;
import br.com.meli.partidafutebolapi.model.Partida;
import br.com.meli.partidafutebolapi.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartidaService {
    @Autowired
    public PartidaRepository repository;
    public ResponseEntity cadastrarPartida(CadastroPartidaDto cadastro){
        Partida partida = new Partida();

        partida.setClubeMandante(cadastro.getClubeMandante());
        partida.setClubeVisitante(cadastro.getClubeVisitante());
        partida.setGoalsMandante(cadastro.getGoalsMandante());
        partida.setGoalsVisitante(cadastro.getGoalsVisitante());
        partida.setNomeEstadio(cadastro.getEstadio());
        partida.setDataHoraPartida(cadastro.getDataHoraPartida());

        repository.save(partida);

        return ResponseEntity.status(201).build();
    }

    public List<CadastroPartidaDto> getAllPartidas(){
        List<Partida> partidas = repository.findAll();
        return convertToListPartidasDto(partidas);
    }

    public CadastroPartidaDto alteraPartida(AlteraPartidaDto alteraPartida, Long id) {

        Optional<Partida> partidas = repository.findById(id);
        if(partidas.isPresent()){
            Partida partidaAlterada = partidas.get();
            partidaAlterada.setClubeMandante(alteraPartida.getClubeMandante());
            partidaAlterada.setClubeVisitante(alteraPartida.getClubeVisitante());
            partidaAlterada.setGoalsMandante(alteraPartida.getGoalsMandante());
            partidaAlterada.setGoalsVisitante(alteraPartida.getGoalsVisitante());
            partidaAlterada.setDataHoraPartida(alteraPartida.getDataHoraPartida());
            partidaAlterada.setNomeEstadio(alteraPartida.getEstadio());
            repository.save(partidaAlterada);

            return convertToPartidaAlteradaDto(partidaAlterada);

        }else{

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deletePartida(Long id){
            Optional<Partida> partidas = repository.findById(id);

            if(partidas.isEmpty()){
                return ResponseEntity.notFound().build();

            }else{

                 repository.deleteById(id);
                 return ResponseEntity.noContent().build();
            }

    }

    private static List<CadastroPartidaDto> convertToListPartidasDto(List<Partida> partidas) {
        return partidas.stream().map(CadastroPartidaDto::new).collect(Collectors.toList());
    }
    private CadastroPartidaDto convertToPartidaAlteradaDto(Partida partida) {
        CadastroPartidaDto cadastro = new CadastroPartidaDto();

        cadastro.setId(partida.getId());
        cadastro.setClubeMandante(partida.getClubeMandante());
        cadastro.setClubeVisitante(partida.getClubeVisitante());
        cadastro.setGoalsMandante(partida.getGoalsMandante());
        cadastro.setGoalsVisitante(partida.getGoalsVisitante());
        cadastro.setDataHoraPartida(partida.getDataHoraPartida());
        cadastro.setEstadio(partida.getNomeEstadio());

        return cadastro;
    }
}