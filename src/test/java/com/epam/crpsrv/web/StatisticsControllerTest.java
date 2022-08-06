package com.epam.crpsrv.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import com.epam.crpsrv.model.OldestNewestMinMaxDto;
import com.epam.crpsrv.service.StatisticService;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(StatisticsController.class)
class StatisticsControllerTest extends BaseControllerTest {

    private final static String URL_BASE = "/statistics";
    private final static String URL_OLDEST_NEWEST_MIN_MAX = URL_BASE + "/oldest-newest-min-max";

    private final TypeReference TYPE_REFERENCE_OLDEST_NEWEST_MIN_MAX_DTOS =
            new TypeReference<List<OldestNewestMinMaxDto>>() {
            };

    @MockBean
    StatisticService statisticService;

    @Test
    void oldestNewestMinMax() throws Exception {
        var expectedDto = OldestNewestMinMaxDto.builder()
                .maxPrice(BigDecimal.TEN)
                .minPrice(BigDecimal.ONE)
                .build();

        var expectedResult = List.of(expectedDto);

        var month = 2;
        var year = 2050;

        doReturn(expectedResult).when(statisticService).oldestNewestMinMax(month, year);

        var url = URL_OLDEST_NEWEST_MIN_MAX + "?month=" + month + "&year=" + year;

        var actualResult = (List<OldestNewestMinMaxDto>) executeGet(url, TYPE_REFERENCE_OLDEST_NEWEST_MIN_MAX_DTOS);

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}