package com.epam.crpsrv.util;

import com.epam.crpsrv.dto.NormalizedRangeDto;
import com.epam.crpsrv.dto.OldestNewestMinMaxDto;
import com.epam.crpsrv.model.view.NormalizedRangeView;
import com.epam.crpsrv.model.view.OldestNewestMinMaxView;
import java.util.List;
import java.util.stream.Collectors;

public class ViewToDtoMapperUtil {

    public static NormalizedRangeDto normalizedRangeViewToDto(NormalizedRangeView view) {
        return NormalizedRangeDto.builder()
                .symbol(view.getSymbol())
                .normalizedRange(view.getNormalizedRange())
                .build();
    }

    public static List<NormalizedRangeDto> normalizedRangeViewToDtoList(List<NormalizedRangeView> viewList) {
        return viewList.stream()
                .map(v -> normalizedRangeViewToDto(v))
                .collect(Collectors.toList());

    }

    public static OldestNewestMinMaxDto oldestNewestMinMaxViewToDto(OldestNewestMinMaxView view) {
        return OldestNewestMinMaxDto.builder()
                .symbol(view.getSymbol())
                .minPrice(view.getMinPrice())
                .maxPrice(view.getMaxPrice())
                .newestPrice(view.getNewestPrice())
                .oldestPrice(view.getOldestPrice())
                .build();
    }

    public static List<OldestNewestMinMaxDto> oldestNewestMinMaxViewToDtoList(List<OldestNewestMinMaxView> viewList) {
        return viewList.stream()
                .map(v -> oldestNewestMinMaxViewToDto(v))
                .collect(Collectors.toList());

    }
}