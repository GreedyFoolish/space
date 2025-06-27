package com.example.space.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.pagination")
@Setter
@Getter
public class PaginationProperties {

    private int defaultPageIndex;
    private int defaultPageSize;
    private int minPageIndex;
    private int minPageSize;
    private int maxPageSize;

}
