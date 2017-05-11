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

    @Autowired
    private RestTemplate rest;

    public ExchangeModel getExchangeModel(){
        String url = "http://api.fixer.io/latest?symbols=USD&base=PLN";
        return rest.getForObject(url, ExchangeModel.class);
    }

    public ExchangeModel getExchangeModel(String base){
        return rest.getForObject(getFormatUrl(base, ""), ExchangeModel.class);
    }

    public ExchangeModel getExchangeModel(String base, String symbol){
        return rest.getForObject(getFormatUrl(base,symbol), ExchangeModel.class);
    }

    private String getFormatUrl(String base, String symbol){
        String localURL = "http://api.fixer.io/latest?";
        String BASE = "base=%s&";
        String SYMBOLS = "symbols=%s&";

        localURL += (base.isEmpty()) ? "" : BASE;
        localURL += (symbol.isEmpty()) ? "" : SYMBOLS;
        return String.format(localURL, base, symbol);
    }
}
