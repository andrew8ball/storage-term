package ru.vtb.productprofile.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vtb.productprofile.domain.term.Card;
import ru.vtb.productprofile.domain.term.LogOperations;
import ru.vtb.productprofile.domain.term.dto.BuyResponseDto;
import ru.vtb.productprofile.domain.term.dto.CardDto;
import ru.vtb.productprofile.domain.term.dto.ClientDto;
import ru.vtb.productprofile.repository.CardRepository;
import ru.vtb.productprofile.repository.ClientRepository;
import ru.vtb.productprofile.repository.LogOperationsRepository;
import ru.vtb.productprofile.service.CardService;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final ClientRepository clientRepository;
    private final LogOperationsRepository logOperationsRepository;

    @Override
    public BuyResponseDto buy(String cardNumber, Double amount) {
        BuyResponseDto result = new BuyResponseDto();
        Optional<Card> card = cardRepository.findByCardNumber(cardNumber);
        if (card.isEmpty()) {
            result.setError("Card not found");
            result.setStatus("Error");
            return result;
        }

        if (card.get().getBalance() < amount) {
            result.setError("Balance less amount");
            result.setStatus("Error");
            return result;
        }

        card.get().setBalance(card.get().getBalance() - amount);
        LogOperations logOperations = new LogOperations();
        logOperations.setAmount(card.get().getBalance());
        logOperations.setDate(LocalDate.now());
        logOperations.setCard(card.get());
        logOperationsRepository.save(logOperations);
        result.setStatus("OK");

        return result;
    }

    @Override
    public CardDto getCardInfo(String cardNumber) {
        Optional<Card> card = cardRepository.findByCardNumber(cardNumber);
        if (card.isEmpty()) {
            return null;
        }
        CardDto cardDto = new CardDto();
        cardDto.setBalance(card.get().getBalance());
        cardDto.setStatusCard(card.get().getStatusCard());
        cardDto.setCardNumber(card.get().getCardNumber());
        cardDto.setPin(card.get().getPin());

        ClientDto clientDto = new ClientDto();
        clientDto.setBad(card.get().getClient().getBad());
        clientDto.setFio(card.get().getClient().getFio());
        clientDto.setPass(card.get().getClient().getPass());

        return cardDto;
    }
}
