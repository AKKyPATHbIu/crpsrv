/*
 * Copyright © 2020 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.crpsrv.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SwaggerConfig {

    @Bean
    public GroupedOpenApi statisticApi() {
        return GroupedOpenApi.builder()
                .group("Statistic")
                .packagesToScan("com.epam.crpsrv.web")
                .pathsToMatch("/statistic/**")
                .build();
    }

    @Bean
    public GroupedOpenApi priceApi() {
        return GroupedOpenApi.builder()
                .group("Price")
                .packagesToScan("com.epam.crpsrv.web")
                .pathsToMatch("/price/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenApi(
            @Value("${application.title}") String appTitle,
            @Value("${application.description}") String appDescription,
            @Value("${application.version}") String appVersion) {

        return new OpenAPI()
                .info(
                        new Info()
                                .title(appTitle + " API")
                                .description(appDescription)
                                .version(appVersion)
                );
    }
}