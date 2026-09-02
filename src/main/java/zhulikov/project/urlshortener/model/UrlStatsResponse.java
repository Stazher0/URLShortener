package zhulikov.project.urlshortener.model;

import lombok.Data;

@Data
public class UrlStatsResponse {

    private String originalUrl;
    private Long totalClicks;
}

