package com.example.ServiceImpl;

import com.example.Entity.ExchangeRate;
import com.example.Entity.ExchangeRateRepository;
import com.example.RestClient.ExchangeClient;
import com.example.Service.ExchangeService;
import com.example.model.ExchangeModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by mateu on 22.04.2017 , 21:08.
 *
 * Implementation of Exchange Service interface
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeClient exchangeClient;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    private Logger logger = LoggerFactory.getLogger(ExchangeServiceImpl.class);

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

    @Override
    public ExchangeModel getExchange(String base) {
        return exchangeClient.getExchangeModel(base);
    }

    @Override
    public void downloadAndSaveRates() {
        Iterable<ExchangeRate> all = exchangeRateRepository.findAll();

        for(ExchangeRate exchangeRate : all){
            logger.info(exchangeRate.toString());
            ExchangeModel exchangeModel = getExchange(exchangeRate.getBase(),exchangeRate.getQuote());
            logger.info((exchangeModel.getRates().getOrDefault(exchangeRate.getBase(), "-1")));
            BigDecimal newRate = new BigDecimal(exchangeModel.getRates().get(exchangeRate.getQuote()));
            exchangeRate.setRate(newRate);
            exchangeRate.setData_up(new Date());
            exchangeRateRepository.save(exchangeRate);
            logger.info("Saved + " + exchangeRate.getBase()+ " -> " + exchangeRate.getQuote());
        }
    }

//    @Override
//    public void downloadAndSaveRates(String base) {
//        ExchangeModel exchangeModel = getExchange(base);
//

//        exchangeModel.getRates().forEach((quote,rate) -> {
//                ExchangeRate exchangeRate = exchangeRateRepository.;
//                exchangeRate.setBase(base);
//                exchangeRate.setQuote(quote);
//                exchangeRate.setRate(new BigDecimal(rate));
//
//            }
//        );

  //     }

}
