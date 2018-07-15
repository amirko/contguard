package com.contguard.track.config;

import com.contguard.track.logic.comparators.DateRangeComparator;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;
import java.util.Date;

@Configuration
@ComponentScan(basePackages = "com.contguard.track.*")
public class Config {

    @Value("${time.range.minutes}")
    private Integer rangeInMinutes;

    @Bean
    public Comparator<Date> trackingDateComparator() {
        return new DateRangeComparator(rangeInMinutes);
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

}
