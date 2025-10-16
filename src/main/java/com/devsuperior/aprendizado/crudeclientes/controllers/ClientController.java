package com.devsuperior.aprendizado.crudeclientes.controllers;

import com.devsuperior.aprendizado.crudeclientes.dto.ClientDTO;
import com.devsuperior.aprendizado.crudeclientes.services.ClientService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable){

        return ResponseEntity.ok().body(clientService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok().body(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO dto) {

        return ResponseEntity.ok().body(clientService.insert(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO dto) {

        return ResponseEntity.ok().body(clientService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
