package br.com.meli.partidafutebolapi.service;

import br.com.meli.partidafutebolapi.dto.AlteraPartidaDto;
import br.com.meli.partidafutebolapi.dto.PartidaDto;
import br.com.meli.partidafutebolapi.model.Partida;
import br.com.meli.partidafutebolapi.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PartidaService {
    @Autowired
    public PartidaRepository repository;
    public void cadastrarPartida(PartidaDto cadastro){
        Partida partida = new Partida();

        validaIntervalo(cadastro.getClubeMandante(),cadastro.getDataHoraPartida());
        validaIntervalo(cadastro.getClubeVisitante(),cadastro.getDataHoraPartida());
        validaEstadioEData(cadastro.getEstadio(), cadastro.getDataHoraPartida());
        validaHora(cadastro.getDataHoraPartida());

        partida.setClubeMandante(cadastro.getClubeMandante());
        partida.setClubeVisitante(cadastro.getClubeVisitante());
        partida.setGoalsMandante(cadastro.getGoalsMandante());
        partida.setGoalsVisitante(cadastro.getGoalsVisitante());
        partida.setNomeEstadio(cadastro.getEstadio());
        partida.setDataHoraPartida(cadastro.getDataHoraPartida());

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

            validaIntervalo(alteraPartida.getClubeMandante(),alteraPartida.getDataHoraPartida());
            validaIntervalo(alteraPartida.getClubeVisitante(),alteraPartida.getDataHoraPartida());
            validaEstadioEData(alteraPartida.getEstadio(), alteraPartida.getDataHoraPartida());
            validaHora(alteraPartida.getDataHoraPartida());

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

    public void deletePartida(Long id){
        Optional<Partida> partidas = repository.findById(id);

        if(partidas.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            repository.deleteById(id);
        }
    }

    private void validaHora(LocalDateTime dataHora){
        List<Partida> partidas = repository.findAll();
        List <LocalDateTime> datas = partidas.stream().map(Partida::getDataHoraPartida).toList();

            LocalTime horarioLimiteMax = LocalTime.of(22,00,01);
            LocalTime horarioLimiteMin = LocalTime.of(07,59,59);
            LocalTime  hr = dataHora.toLocalTime();

            if(hr.isBefore(horarioLimiteMin)   || hr.isAfter(horarioLimiteMax)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Horario da partida permitido depois das 8 hrs e antes das 22 hrs");
        }
    }
    private void validaEstadioEData(String nomeEstadio, LocalDateTime dataPartida){
        List <Partida> estadios = repository.findAllByNomeEstadioEqualsIgnoreCase(nomeEstadio);
        List <LocalDateTime> dataHora = estadios.stream()
                .map(Partida::getDataHoraPartida)
                .toList();

        for(LocalDateTime data : dataHora){

            if(data.toLocalDate().isEqual(dataPartida.toLocalDate())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Estádio já possui uma partida cadastrada nesta data");}
        }
    }

    private void validaIntervalo(String clube,LocalDateTime data){
        List<Partida> clubes = repository.findAllByClubeMandanteOrClubeVisitante(clube,clube);

        List<LocalDateTime> dataHora = clubes.stream().
                map(Partida::getDataHoraPartida).
                toList();

        dataHora.forEach((dias ->{
            Duration duration = Duration.between(dias, data);
            Long diferencaEntreDias = Math.abs(duration.toDays());

            if(diferencaEntreDias <= 2){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cadastro da partida permitido 2 dias depois da ultima partida");
            }
        }));

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