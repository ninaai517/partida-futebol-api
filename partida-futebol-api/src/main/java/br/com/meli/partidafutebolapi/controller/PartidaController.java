package br.com.meli.partidafutebolapi.controller;

import br.com.meli.partidafutebolapi.dto.AlteraPartidaDto;
import br.com.meli.partidafutebolapi.dto.PartidaDto;
import br.com.meli.partidafutebolapi.service.BuscaService;
import br.com.meli.partidafutebolapi.service.PartidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partida")
public class PartidaController {

    @Autowired
    public PartidaService service;

    @Autowired
    public BuscaService buscaService;

    @PostMapping
    public ResponseEntity cadastrarPartida(@RequestBody @Valid PartidaDto cadastro){
        service.cadastrarPartida(cadastro);

        return ResponseEntity.status(201).build();
    }

    @GetMapping()
    public List<PartidaDto> getAllPartidas(){

        return service.getAllPartidas();
    }

    @GetMapping("/{estadio}")
    public List<PartidaDto> getAllPartidasPorEstadio(@PathVariable String estadio){
        return buscaService.getAllEstadios(estadio);
    }

    @PutMapping(path="/{id}")
    public PartidaDto alterarPartida(@RequestBody AlteraPartidaDto alteraPartida , @PathVariable("id") Long id){
        return service.alteraPartida(alteraPartida, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deletePartida(@PathVariable Long id){

            service.deletePartida(id);

            return ResponseEntity.noContent().build();
    }

}