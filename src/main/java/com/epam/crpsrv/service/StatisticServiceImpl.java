package com.epam.crpsrv.service;

import com.epam.crpsrv.model.OldestNewestMinMaxDto;
import com.epam.crpsrv.repository.QuoteRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    QuoteRepository quoteRepository;

    @Override
    public List<OldestNewestMinMaxDto> oldestNewestMinMax(Date dateFrom, Date dateTo) {
        return quoteRepository.oldestNewestMinMax(dateFrom, dateTo);
    }

    @Override
    public List<OldestNewestMinMaxDto> oldestNewestMinMax(int month, int year) {
        var calendar = new GregorianCalendar(year, month - 1, 1, 0, 0, 0);
        var dateFrom = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        var dateTo = calendar.getTime();

        return quoteRepository.oldestNewestMinMax(dateFrom, dateTo);
    }
}