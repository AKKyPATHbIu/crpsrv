package com.epam.crpsrv.service;

import com.epam.crpsrv.model.OldestNewestMinMaxDto;
import com.epam.crpsrv.repository.QuoteRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    QuoteRepository quoteRepository;

    @Override
    public List<OldestNewestMinMaxDto> oldestNewestMinMax(LocalDate dateFrom, LocalDate dateTo) {
        return quoteRepository.oldestNewestMinMax(dateFrom, dateTo);
    }

    @Override
    public List<OldestNewestMinMaxDto> oldestNewestMinMax(int month, int year) {
        var dateFrom = LocalDate.of(year, month, 1);
        var dateTo = dateFrom.plusMonths(1);

        return quoteRepository.oldestNewestMinMax(dateFrom, dateTo);
    }
}