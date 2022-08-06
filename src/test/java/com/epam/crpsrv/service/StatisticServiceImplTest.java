package com.epam.crpsrv.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import com.epam.crpsrv.model.OldestNewestMinMaxDto;
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
    void oldestNewestMinMaxByPeriod() {
        var dateFrom = LocalDate.of(2022, 1, 1);
        var dateTo = LocalDate.of(2022, 1, 10);

        var expectedDto = OldestNewestMinMaxDto.builder()
                .minPrice(new BigDecimal("0.8298"))
                .maxPrice(new BigDecimal("0.8337"))
                .symbol("XRP")
                .build();

        var expectedResult = List.of(expectedDto);

        doReturn(expectedResult).when(quoteRepository).oldestNewestMinMax(dateFrom, dateTo);

        var actualResult = statisticService.oldestNewestMinMax(dateFrom, dateTo);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void oldestNewestMinMaxByMonthAndYear() {
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

        doReturn(expectedResult).when(quoteRepository).oldestNewestMinMax(dateFrom, dateTo);

        var actualResult = statisticService.oldestNewestMinMax(month, year);

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}