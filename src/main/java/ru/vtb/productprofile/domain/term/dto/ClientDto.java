package ru.vtb.productprofile.domain.term.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ClientDto {
    private String fio;
    private String pass;
    private Boolean bad;
}
