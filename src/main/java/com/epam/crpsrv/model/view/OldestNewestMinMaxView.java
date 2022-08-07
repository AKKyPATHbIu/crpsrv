package com.epam.crpsrv.model.view;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;

public interface OldestNewestMinMaxView {

    String getSymbol();

    @Value("#{target.min_price}")
    BigDecimal getMinPrice();

    @Value("#{target.max_price}")
    BigDecimal getMaxPrice();

    @Value("#{target.oldest_price}")
    BigDecimal getOldestPrice();

    @Value("#{target.newest_price}")
    BigDecimal getNewestPrice();
}