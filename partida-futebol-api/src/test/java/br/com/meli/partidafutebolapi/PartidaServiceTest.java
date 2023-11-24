package br.com.meli.partidafutebolapi;

import br.com.meli.partidafutebolapi.dto.PartidaDto;
import br.com.meli.partidafutebolapi.model.Partida;
import br.com.meli.partidafutebolapi.repository.PartidaRepository;
import br.com.meli.partidafutebolapi.service.PartidaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.when;

public class PartidaServiceTest {

    @Mock
    private PartidaRepository repository;
    private Partida partida;
    @InjectMocks
    private PartidaService service;
    @Test
    public void testCadastrarPartida(PartidaDto cadastro){
        when(Partida partida = new Partida()).thenReturn(List.copyOf());


        when(repository.save(partida));


    }

}
