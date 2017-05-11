package com.example.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mateu on 05.05.2017 , 00:12.
 *
 * Entity of rates
 */
@Entity
@Data
@Table(name = "exchange_rates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(updatable = false)
    private String base;

    @NotNull
    @Column(updatable = false)
    private String quote;

    @NotNull
    private BigDecimal rate;

    @NotNull
    @Column(updatable = false)
    private Date data_ins;

    private Date data_up;
}
