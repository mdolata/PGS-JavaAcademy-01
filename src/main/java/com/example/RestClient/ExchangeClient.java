package com.example.RestClient;

import com.example.model.ExchangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mateu on 22.04.2017 , 21:04.
 *
 * Service for get exchange from Rest Service
 */
@Service
public class ExchangeClient {
    private final String URL = "http://api.fixer.io/latest?symbols=%s&base=%s";

    @Autowired
    private RestTemplate rest;

    public ExchangeModel getExchangeModel(){
        String url = "http://api.fixer.io/latest?symbols=USD&base=PLN";
        return rest.getForObject(url, ExchangeModel.class);
    }

    public ExchangeModel getExchangeModel(String base, String symbol){
        return rest.getForObject(getFormatUrl(base,symbol), ExchangeModel.class);
    }

    private String getFormatUrl(String base, String symbol){
        return String.format(URL, symbol, base);
    }
}
