package com.epam.crpsrv.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OldestNewestMinMaxDto {

    String symbol;
    BigDecimal minPrice;
    BigDecimal maxPrice;
    BigDecimal oldestPrice;
    BigDecimal newestPrice;
}