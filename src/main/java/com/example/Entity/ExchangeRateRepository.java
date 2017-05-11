package com.example.Entity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mateu on 05.05.2017 , 00:29.
 *
 * Repository for ExchangeRate
 */
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long> {
    List<ExchangeRate> findByBase(String base);

    ExchangeRate findByBaseAndQuote(String base, String quote);

}
