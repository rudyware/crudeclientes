package com.devsuperior.aprendizado.crudeclientes.repositories;

import com.devsuperior.aprendizado.crudeclientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
