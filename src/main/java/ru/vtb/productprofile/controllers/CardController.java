package ru.vtb.productprofile.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.vtb.productprofile.domain.term.dto.BuyResponseDto;
import ru.vtb.productprofile.domain.term.dto.CardDto;
import ru.vtb.productprofile.service.CardService;

@Slf4j
@RestController
@RequestMapping("http://localhost:8082/card/buy")
@RequiredArgsConstructor
@Api(tags = {"Счета"})
@SwaggerDefinition(tags = {
        @Tag(name = "Счета", description = "Позволяет получать счета клиента")
})
public class CardController {
    private final CardService cardService;

    @PostMapping(path = "/buy")
    @ApiOperation(value = "Списать", notes = "Списываем бабки")
    public BuyResponseDto buy(@RequestParam(value = "card") String card,
                              @RequestParam(value = "amount") Double amount) {
        log.info("doBuy()");
        return cardService.buy(card, amount);
    }

    @GetMapping(path = "/info")
    @ApiOperation(value = "Получение инфо", notes = "Получение инфо")
    public CardDto getInfo(@RequestParam(value = "card") String card) {
        log.info("getInfo()");
        return cardService.getCardInfo(card);
    }
}
