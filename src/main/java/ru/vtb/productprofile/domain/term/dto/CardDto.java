package ru.vtb.productprofile.domain.term.dto;

import lombok.Data;
import ru.vtb.productprofile.domain.term.Client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class CardDto {
    private long id;
    private String cardNumber;
    private Client client;
    private String pin;
    private String statusCard;
    private Double balance;
}
