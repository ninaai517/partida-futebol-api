package br.com.meli.partidafutebolapi.service;

import br.com.meli.partidafutebolapi.dto.AlteraPartidaDto;
import br.com.meli.partidafutebolapi.dto.CadastroPartidaDto;
import br.com.meli.partidafutebolapi.model.PartidaModel;
import br.com.meli.partidafutebolapi.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartidaService {

    @Autowired
    public PartidaRepository repository;
    public void cadastrarPartida(CadastroPartidaDto cadastro){
        PartidaModel partida = new PartidaModel();
        partida.setId(cadastro.getId());
        partida.setClubeMandante(cadastro.getClubeMandante());
        partida.setClubeVisitante(cadastro.getClubeVisitante());
        partida.setGoalsMandante(cadastro.getGoalsMandante());
        partida.setGoalsVisitante(cadastro.getGoalsVisitante());
        partida.setNomeEstadio(cadastro.getEstadio());
        partida.setDataHoraPartida(cadastro.getDataHoraPartida());
        repository.save(partida);
    }

    public List<CadastroPartidaDto> getAllPartidas(){
        List<PartidaModel> partidas = repository.findAll();
        return convertToListPartidasDto(partidas);
    }

    public CadastroPartidaDto alteraPartida(AlteraPartidaDto alteraPartida, Long id) {

            Optional<PartidaModel> partidas = repository.findById(id);
            if(partidas.isPresent()){
                PartidaModel partidaAlterada = partidas.get();
                partidaAlterada.setClubeMandante(alteraPartida.getClubeMandante());
                partidaAlterada.setClubeVisitante(alteraPartida.getClubeVisitante());
                partidaAlterada.setGoalsMandante(alteraPartida.getGoalsMandante());
                partidaAlterada.setGoalsVisitante(alteraPartida.getGoalsVisitante());
                partidaAlterada.setDataHoraPartida(alteraPartida.getDataHoraPartida());
                partidaAlterada.setNomeEstadio(alteraPartida.getEstadio());
                repository.save(partidaAlterada);

                return convertToPartidaAlteradaDto(partidaAlterada);
            }

            return null;
    }

    public void deletePartida(Long id){
        repository.deleteById(id);
    }

    private static List<CadastroPartidaDto> convertToListPartidasDto(List<PartidaModel> partidas) {
        return partidas.stream().map(CadastroPartidaDto::new).collect(Collectors.toList());
    }
    private CadastroPartidaDto convertToPartidaAlteradaDto(PartidaModel partida) {
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


