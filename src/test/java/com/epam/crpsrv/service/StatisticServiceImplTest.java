package com.epam.crpsrv.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import com.epam.crpsrv.dto.NormalizedRangeDto;
import com.epam.crpsrv.dto.OldestNewestMinMaxDto;
import com.epam.crpsrv.repository.QuoteRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StatisticServiceImplTest {

    @Mock
    QuoteRepository quoteRepository;

    @InjectMocks
    StatisticServiceImpl statisticService;

    @Test
    void calcOldestNewestMinMaxByPeriod() {
        var dateFrom = LocalDate.of(2022, 1, 1);
        var dateTo = LocalDate.of(2022, 1, 10);

        var expectedDto = OldestNewestMinMaxDto.builder()
                .minPrice(new BigDecimal("0.8298"))
                .maxPrice(new BigDecimal("0.8337"))
                .symbol("XRP")
                .build();

        var expectedResult = List.of(expectedDto);

        doReturn(expectedResult).when(quoteRepository).calcOldestNewestMinMax(dateFrom, dateTo);

        var actualResult = statisticService.calcOldestNewestMinMax(dateFrom, dateTo);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calcOldestNewestMinMaxByMonthAndYear() {
        var year = 2022;
        var month = 1;

        var dateFrom = LocalDate.of(2022, 1, 1);
        var dateTo = LocalDate.of(2022, 2, 1);

        var expectedDto = OldestNewestMinMaxDto.builder()
                .minPrice(new BigDecimal("46813.21"))
                .maxPrice(new BigDecimal("47336.98"))
                .symbol("BTC")
                .build();

        var expectedResult = List.of(expectedDto);

        doReturn(expectedResult).when(quoteRepository).calcOldestNewestMinMax(dateFrom, dateTo);

        var actualResult = statisticService.calcOldestNewestMinMax(month, year);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calcOldestNewestMinMaxByMonthAndYearAndSymbol() {
        var year = 2022;
        var month = 1;

        var dateFrom = LocalDate.of(2022, 1, 1);
        var dateTo = LocalDate.of(2022, 2, 1);

        var symbol = "BTC";

        var expectedResult = OldestNewestMinMaxDto.builder()
                .minPrice(new BigDecimal("35813.21"))
                .maxPrice(new BigDecimal("336.98"))
                .symbol(symbol)
                .build();

        doReturn(expectedResult).when(quoteRepository).calcOldestNewestMinMax(dateFrom, dateTo, symbol);

        var actualResult = statisticService.calcOldestNewestMinMax(month, year, symbol);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calcNormalizedPrice() {
        var expectedResult = List.of(
                NormalizedRangeDto.builder()
                        .symbol("XRP")
                        .normalizedRange(new BigDecimal("0.21"))
                        .build(),
                NormalizedRangeDto.builder()
                        .symbol("BTC")
                        .normalizedRange(new BigDecimal("0.55"))
                        .build()
        );

        doReturn(expectedResult).when(quoteRepository).calcNormalizedRange();

        var actualResult = statisticService.calcNormalizedRange();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calcNormalizedPriceByCrypto() {
        var symbol = "XRP";
        var expectedResult = NormalizedRangeDto.builder()
                .symbol(symbol)
                .normalizedRange(new BigDecimal("0.21"))
                .build();

        var date = LocalDate.of(2022, 1, 1);

        doReturn(expectedResult).when(quoteRepository).calcNormalizedRange(date, symbol);

        var actualResult = statisticService.calcNormalizedRange(date, symbol);

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}