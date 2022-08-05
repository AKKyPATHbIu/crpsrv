package com.epam.crpsrv.quoteparser;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = false)
public class QuoteDto {

    @ToString.Include
    private Date timestamp;

    @ToString.Include
    private String symbol;

    @ToString.Include
    private BigDecimal price;
}