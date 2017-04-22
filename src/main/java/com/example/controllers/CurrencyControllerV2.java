package com.example.controllers;

import com.example.Service.ExchangeService;
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


    @RequestMapping("checkService")
    public String checkService(){
        Map<String, String> rates = service.getExchange().getRates();

        rates.forEach((x,y) -> System.out.println(x + y));

        return service.getExchange().getBase();
    }

    @RequestMapping("isWorking")
    public Boolean isServiceWorking(){
        return Boolean.TRUE;
    }

    @RequestMapping("exchange/{value}")
    public String exchangeValue(@PathVariable Long value,
                                @RequestParam(name = "f") String from,
                                @RequestParam(name = "t") String to){
        StringBuilder resultBuilder = new StringBuilder();

        Currency currencyFrom;
        Currency currencyTo;
        BigDecimal rateExchange; // = new BigDecimal(Math.random() * 10 ).setScale(2, BigDecimal.ROUND_DOWN);
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

        final Map<String, String> rates = service.getExchange(currencyFrom.toString(),
                currencyTo.toString())
                .getRates();
        rateExchange =  new BigDecimal(rates.get(currencyTo.toString()));

        BigDecimal resultExchange = bigDecimalValue.multiply(rateExchange);

        resultBuilder.append(bigDecimalValue.toString())
                .append(currencyFrom.toString())
                .append(" equals ")
                .append(resultExchange)
                .append(currencyTo);

        return resultBuilder.toString();
    }
}
