package com.course.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Component
public class TimeZoneConfig {
    //    用于设置默认时区
    @Value("${spring.jackson.time-zone}")
    private String timeZone;

    @PostConstruct
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
    }
}
