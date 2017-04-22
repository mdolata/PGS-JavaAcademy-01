package com.example.Service;

import com.example.model.ExchangeModel;

/**
 * Created by mateu on 22.04.2017 , 20:51.
 *
 * Interface for ExchangeService's implementations
 *
 * It will be expand with new methods.
 */
public interface ExchangeService {
    ExchangeModel getExchange();

    ExchangeModel getExchange(String base, String to);
}
