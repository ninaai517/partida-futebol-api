package br.com.meli.partidafutebolapi.service;

import br.com.meli.partidafutebolapi.dto.PartidaDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PartidaServiceTest {
    @Test
    void validarMetodoPost(PartidaDto dto){
        //Arrange
        PartidaService service = new PartidaService();
        //ACT
        service.cadastrarPartida(new PartidaDto());
        //Assert
        Assertions.assertDoesNotThrow(service.validaIntervalo("Palmeiras", LocalDateTime.of(2023,10,15,16,00,00)));
    }

}