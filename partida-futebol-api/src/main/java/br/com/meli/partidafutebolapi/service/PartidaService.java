package br.com.meli.partidafutebolapi.service;

import br.com.meli.partidafutebolapi.dto.AlteraPartidaDto;
import br.com.meli.partidafutebolapi.dto.PartidaDto;
import br.com.meli.partidafutebolapi.model.Partida;
import br.com.meli.partidafutebolapi.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartidaService {
    @Autowired
    public PartidaRepository repository;
    public void cadastrarPartida(PartidaDto cadastro){
        Partida partida = new Partida();

            partida.setClubeMandante(cadastro.getClubeMandante());
            partida.setClubeVisitante(cadastro.getClubeVisitante());
            partida.setGoalsMandante(cadastro.getGoalsMandante());
            partida.setGoalsVisitante(cadastro.getGoalsVisitante());
            partida.setNomeEstadio(cadastro.getEstadio());

            if(!(cadastro.validaHora())){
                partida.setDataHoraPartida(cadastro.getDataHoraPartida());
            }

            repository.save(partida);
    }

    public List<PartidaDto> getAllPartidas(){
        List<Partida> partidas = repository.findAll();
        return convertToListPartidasDto(partidas);
    }

    public PartidaDto alteraPartida(AlteraPartidaDto alteraPartida, Long id) {

        Optional<Partida> partidas = repository.findById(id);

        if(partidas.isPresent()){
            Partida partidaAlterada = partidas.get();

            partidaAlterada.setClubeMandante(alteraPartida.getClubeMandante());
            partidaAlterada.setClubeVisitante(alteraPartida.getClubeVisitante());
            partidaAlterada.setGoalsMandante(alteraPartida.getGoalsMandante());
            partidaAlterada.setGoalsVisitante(alteraPartida.getGoalsVisitante());

            if(!(alteraPartida.validaHora())){
                partidaAlterada.setDataHoraPartida(alteraPartida.getDataHoraPartida());
            }

            partidaAlterada.setNomeEstadio(alteraPartida.getEstadio());

            repository.save(partidaAlterada);

            return convertToPartidaAlteradaDto(partidaAlterada);

        }else{

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deletePartida(Long id){
            Optional<Partida> partidas = repository.findById(id);

            if(partidas.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }else{
                repository.deleteById(id);
            }
    }

    private static List<PartidaDto> convertToListPartidasDto(List<Partida> partidas) {
        return partidas.stream().map(PartidaDto::new).collect(Collectors.toList());
    }
    private PartidaDto convertToPartidaAlteradaDto(Partida partida) {
        PartidaDto cadastro = new PartidaDto();

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
