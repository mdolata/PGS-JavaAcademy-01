package com.example.controllers;

import com.example.Service.ExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

/**
 * Created by mateu on 22.04.2017 , 21:10.
 *
 * Controller for api2
 */
@RestController
@RequestMapping("api2")
public class CurrencyControllerV2 {

    @Autowired
    private ExchangeService service;

    private Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @RequestMapping("checkService")
    public String checkService() {
        Map<String, String> rates = service.getExchange().getRates();

        rates.forEach((x, y) -> logger.info(x + y));

        return service.getExchange().getBase();
    }

    @RequestMapping("isWorking")
    public Boolean isServiceWorking() {
        return Boolean.TRUE;
    }

    @RequestMapping("exchange/{value:.+}")
    public String exchangeValue(@PathVariable String value,
                                @RequestParam(name = "base") String from,
                                @RequestParam(name = "to") String to) {

        StringBuilder resultBuilder = new StringBuilder();

        Currency currencyFrom;
        Currency currencyTo;
        BigDecimal rateExchange;
        BigDecimal valueBD;

        try {
            valueBD = new BigDecimal(value).setScale(2, BigDecimal.ROUND_DOWN);

            // is that necessary to null checks?
            // RequestParams has param named required that is default true
            if (from == null || to == null) {
                throw new RuntimeException();
            }
            currencyFrom = getCurrencyFromString(from);
            currencyTo = getCurrencyFromString(to);
        } catch (NumberFormatException e) {
            return "Wrong value!";
        } catch (IllegalArgumentException e) {
            return "Have to choose valid currency";
        } catch (RuntimeException e) {
            return "Have to choose both values!Runtime";
        }

        final Map<String, String> rates = service
                .getExchange(currencyFrom.toString(), currencyTo.toString())
                .getRates();

        rateExchange = new BigDecimal(rates.get(currencyTo.toString()));
        BigDecimal exchangeResult = valueBD.multiply(rateExchange).setScale(4, BigDecimal.ROUND_DOWN);

        resultBuilder.append(valueBD)
                .append(currencyFrom)
                .append(" for ")
                .append(rateExchange)
                .append(" equals ")
                .append(exchangeResult)
                .append(currencyTo);

        return resultBuilder.toString();
    }

    private Currency getCurrencyFromString(String currency) throws IllegalArgumentException {
        return Currency.getInstance(currency.toUpperCase());
    }
}
