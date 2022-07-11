package com.jdreyes.webapi.prestamos.rest;

import com.jdreyes.webapi.prestamos.model.dto.CobrosDia;
import com.jdreyes.webapi.prestamos.model.dto.ResponseBaseFactory;
import com.jdreyes.webapi.prestamos.model.dto.cliente.ClienteDto;
import com.jdreyes.webapi.prestamos.service.ClienteService;
import com.jdreyes.webapi.prestamos.service.dtos.FuncionarioDto;
import com.jdreyes.webapi.prestamos.service.utils.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos/clientes/")
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("all")
    public ResponseEntity<?> all(){
        FuncionarioDto user = ContextUtils.getCurrentUser();
        try {
            log.info("[{}] obteniedo los clientes", user.getUsername());
            List<ClienteDto> clientes = clienteService.getClientes();
            log.info("[{}] se encontraron {} clientes", user.getUsername(), clientes.size());
            return ResponseBaseFactory.wrap(clientes);
        } catch (Exception e) {
            log.error(
                    String.format(
                            "[%s] Error en \"obtener clientes\" -> \"%s\"",
                            user.getUsername(), getClass().getSimpleName()),
                    e);
            return ResponseBaseFactory.wrap(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("find/withid/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        FuncionarioDto user = ContextUtils.getCurrentUser();
        try {
            log.info("[{}] buscando al cliente por id={}", user.getUsername(), id);
            List<ClienteDto> clientes = clienteService.getById(id);
            log.info("[{}] se encontraron {} coincidencia de cliente con id={}", user.getUsername(), clientes.size(), id);
            return ResponseBaseFactory.wrap(clientes);
        } catch (Exception e) {
            log.error(
                    String.format(
                            "[%s] Error en \"obtener clientes por id\" -> \"%s\"",
                            user.getUsername(), getClass().getSimpleName()),
                    e);
            return ResponseBaseFactory.wrap(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
