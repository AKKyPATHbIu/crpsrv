package com.epam.crpsrv.model.view;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;

public interface NormalizedRangeView {

    String getSymbol();

    @Value("#{target.norm_range}")
    BigDecimal getNormalizedRange();
}