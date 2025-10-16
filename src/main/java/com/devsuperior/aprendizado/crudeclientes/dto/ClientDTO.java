package com.devsuperior.aprendizado.crudeclientes.dto;

import com.devsuperior.aprendizado.crudeclientes.entities.Client;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ClientDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 3, max = 30, message = "O nome não deve ser inferior a 3 nem Superior a 30")
    private String name;

    @NotBlank(message = "Campo requerido")
    private String cpf;

    @Positive(message = "O valor deve ser positivo")
    private Double income;

    @PastOrPresent(message = "A data não deve ser futura")
    private LocalDate birthDate;

    @PositiveOrZero(message = "Não são permitidos números negativos")
    private Integer children;

    public ClientDTO() {
    }

    public ClientDTO(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
