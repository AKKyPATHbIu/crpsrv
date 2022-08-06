package com.epam.crpsrv.cryptopriceparser;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.crpsrv.service.CryptoPriceService;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

@ExtendWith(MockitoExtension.class)
class CryptoPriceValuesLoaderTest {

    @Mock
    ResourcePatternResolver resourcePatternResolver;

    @Mock
    CryptoPriceService cryptoPriceService;

    @InjectMocks
    CryptoPriceValuesLoader cryptoPriceValuesLoader;

    @Test
    void onApplicationEvent() throws IOException {
        var mockedRefreshEvent = mock(ContextRefreshedEvent.class);
        var mockedResource = mock(Resource.class);
        var mockedInputStream = mock(InputStream.class);

        var expectedContent = new byte[]{1, 2, 3};

        doReturn(new Resource[]{mockedResource}).when(resourcePatternResolver).getResources("classpath:/prices/*.csv");
        doReturn(mockedInputStream).when(mockedResource).getInputStream();
        doReturn(expectedContent).when(mockedInputStream).readAllBytes();

        cryptoPriceValuesLoader.onApplicationEvent(mockedRefreshEvent);

        verify(cryptoPriceService, times(1)).saveFromByteContent(expectedContent);
    }
}