package com.epam.crpsrv.web;

import com.epam.crpsrv.dto.NormalizedPriceDto;
import com.epam.crpsrv.dto.OldestNewestMinMaxDto;
import com.epam.crpsrv.service.StatisticService;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("statistics")
public class StatisticsController {

    @Autowired
    StatisticService statisticService;

    @GetMapping(value = "oldest-newest-min-max", produces = {"application/json"})
    public List<OldestNewestMinMaxDto> calcOldestNewestMinMax(
            @RequestParam @Min(1) @Max(12) int month,
            @RequestParam @Min(2022) int year) {

        return statisticService.calcOldestNewestMinMax(month, year);
    }

    @GetMapping(value = "oldest-newest-min-max/{symbol}", produces = {"application/json"})
    public OldestNewestMinMaxDto calcOldestNewestMinMaxbySymbol(
            @RequestParam @Min(1) @Max(12) int month,
            @RequestParam @Min(2022) int year,
            @PathVariable String symbol) {

        return statisticService.calcOldestNewestMinMax(month, year, symbol);
    }

    @GetMapping(value = "normalized-price", produces = {"application/json"})
    public List<NormalizedPriceDto> calcNormalizedPrice() {
        return statisticService.calcNormalizedPrice();
    }
}