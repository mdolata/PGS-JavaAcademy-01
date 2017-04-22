package com.example.ServiceImpl;

import com.example.RestClient.ExchangeClient;
import com.example.Service.ExchangeService;
import com.example.model.ExchangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mateu on 22.04.2017 , 21:08.
 *
 * Implementation of Exchange Service interface
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeClient exchangeClient;

    /*
    * @return default value
    */
    @Override
    public ExchangeModel getExchange() {
        return exchangeClient.getExchangeModel();
    }

    @Override
    public ExchangeModel getExchange(String base, String to) {
        return exchangeClient.getExchangeModel(base, to);
    }
}
