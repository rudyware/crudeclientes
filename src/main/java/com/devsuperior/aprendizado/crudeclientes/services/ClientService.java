package com.devsuperior.aprendizado.crudeclientes.services;

import com.devsuperior.aprendizado.crudeclientes.dto.ClientDTO;
import com.devsuperior.aprendizado.crudeclientes.entities.Client;
import com.devsuperior.aprendizado.crudeclientes.repositories.ClientRepository;
import com.devsuperior.aprendizado.crudeclientes.services.exceptions.DatabaseException;
import com.devsuperior.aprendizado.crudeclientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {

        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){

        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(ClientDTO::new);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();

        return copyDtoToEntity(dto, client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {

        try {

            Client client = clientRepository.getReferenceById(id);

            return copyDtoToEntity(dto, client);

        } catch (EntityNotFoundException e) {

            throw new ResourceNotFoundException("Recurso não encontrado");
        }

    }

    @Transactional
    public void delete(Long id) {

        if(!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado.");
        }
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Tentativa de violação de integridade.");
        }
    }

    private ClientDTO copyDtoToEntity(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());

        client = clientRepository.save(client);
        return new ClientDTO(client);
    }
}
