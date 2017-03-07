package com.example.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Currency;

/**
 * Created by mateu on 07.03.2017.
 */
@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @RequestMapping("/")
    public String home(){
        return "choose your value after slash";
    }

    @RequestMapping("/{number}")
    public Long multiplyByTwo(@PathVariable Long number){
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
        String result;
        try {
            Currency currencyFrom = Currency.getInstance(from.toUpperCase());
            result = value + currencyFrom.toString() + " equals ";

        } catch (NullPointerException e){
            result = "Choose first currency!";
            return result;
        } catch (IllegalArgumentException e){
            result = "Choose valid first currency!";
            return result;
        }

        try {
            Currency currencyTo = Currency.getInstance(to.toUpperCase());
            result += (BigDecimal.valueOf(Math.random()*10).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()) * value + currencyTo.toString();
        } catch (NullPointerException e){
            result = "Choose second currency!";
            return result;
        } catch (IllegalArgumentException e){
            result = "Choose valid second currency!";
            return result;
        }

        return result;
    }
}
