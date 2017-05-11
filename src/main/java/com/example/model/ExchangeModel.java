package com.example.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Map;

/**
 * Created by mateu on 22.04.2017 , 20:45.
 *
 * Model for keep rates
 */
@Data
public class ExchangeModel {

    private String base;

    private String date;

    private Map<String,String> rates;

}
