package com.jomilanez.repository;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class IndexNameProvider {

    public String getIndexName() {
        return "articles-" + ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

}
