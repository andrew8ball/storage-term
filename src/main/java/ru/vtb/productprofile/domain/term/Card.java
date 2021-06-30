package ru.vtb.productprofile.domain.term;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;



@Setter
@Getter
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cardNumber;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "client_id")
    private Client client;
    private String pin;
    private String statusCard;
    private Double balance;
}
