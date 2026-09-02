package zhulikov.project.urlshortener.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import zhulikov.project.urlshortener.model.Url;
import zhulikov.project.urlshortener.model.UrlStatsResponse;
import zhulikov.project.urlshortener.repository.ClickRepo;
import zhulikov.project.urlshortener.repository.UrlRepo;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final UrlRepo urlRepo;
    private final ClickRepo clickRepo;

    public UrlStatsResponse getUrlStats(String shortKey){
        Url url = urlRepo.findByShortKey(shortKey)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Link not found: " + shortKey
                ));

        UrlStatsResponse response = new UrlStatsResponse();
        response.setOriginalUrl(url.getOriginalUrl());
        response.setTotalClicks(getTotalClicksCount(url));

        return response;
    }

    public long getTotalClicksCount(Url url){
        return clickRepo.countByUrl(url);
    }
}
