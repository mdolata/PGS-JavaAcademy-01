package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Currency;


/**
 * Created by mateu on 07.03.2017.
 *
 * Controller for currency convert
 */
@RestController
@RequestMapping("/currency")
public class CurrencyController {


    private Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    public String home2(){
        return "Bad request";
    }

    @RequestMapping(value = {"/"," * ","/something"})
    public String home(){
        return "choose your value after slash";
    }

    @RequestMapping("/{number}")
    public Long multiplyByTwo(@PathVariable Long number){
        logger.info("metoda multipleByTwo");
        return 2 * number;
    }

    @RequestMapping("/num/{value}")
    public String valueAndCurrency(@PathVariable Long value, @RequestParam String currency){
        return value + " " + currency;
    }

    @RequestMapping("/exchange/{value}")
    public String exchangeValue(@PathVariable Long value,
                                @RequestParam String from,
                                @RequestParam String to){
        StringBuilder resultBuilder = new StringBuilder();

        Currency currencyFrom;
        Currency currencyTo;
        BigDecimal rateExchange = new BigDecimal(Math.random() * 10 ).setScale(2, BigDecimal.ROUND_DOWN);
        BigDecimal bigDecimalValue = new BigDecimal(String.valueOf(value));
        try {
            currencyFrom = Currency.getInstance(from.toUpperCase());
        } catch (NullPointerException e){
            return "Choose first currency!";
        } catch (IllegalArgumentException e){
            return "Choose valid first currency!";
        }

        try {
            currencyTo = Currency.getInstance(to.toUpperCase());
        } catch (NullPointerException e){
            return "Choose second currency!";
        } catch (IllegalArgumentException e){
            return "Choose valid second currency!";
        }

        logger.info("rate " + rateExchange.toString());

        BigDecimal resultExchange = bigDecimalValue.multiply(rateExchange);

        resultBuilder.append(bigDecimalValue.toString())
                .append(currencyFrom.toString())
                .append(" equals ")
                .append(resultExchange)
                .append(currencyTo);

        return resultBuilder.toString();
    }
}
