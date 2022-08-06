package com.epam.crpsrv.cryptoprice;

import com.epam.crpsrv.exception.CrpSrvException;
import com.epam.crpsrv.service.CryptoPriceService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CryptoPriceValuesLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final String PRICES_LOCATION_PATTERN = "classpath:/prices/*.csv";

    @Autowired
    CryptoPriceService cryptoPriceService;

    @Autowired
    ResourcePatternResolver resourcePatternResolver;

    @Override
    @Transactional(noRollbackFor = CrpSrvException.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Resource[] resources;

        try {
            resources = resourcePatternResolver.getResources(PRICES_LOCATION_PATTERN);
        } catch (IOException ex) {
            log.error("Failed to import price values");
            return;
        }

        for (Resource r : resources) {
            try {
                byte[] bytes = r.getInputStream().readAllBytes();
                cryptoPriceService.saveFromByteContent(bytes);
            } catch (IOException ex) {
                log.error(String.format("Failed to import %s", r.getFilename()));
            } catch (CrpSrvException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }
}