package ru.vtb.productprofile.service;

import ru.vtb.productprofile.domain.term.dto.BuyResponseDto;
import ru.vtb.productprofile.domain.term.dto.CardDto;

public interface CardService {

    BuyResponseDto buy(String card, Double amount);

    CardDto getCardInfo(String card);
}
