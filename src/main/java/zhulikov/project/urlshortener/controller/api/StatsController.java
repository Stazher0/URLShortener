package zhulikov.project.urlshortener.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhulikov.project.urlshortener.model.UrlStatsResponse;
import zhulikov.project.urlshortener.service.StatsService;

@RestController
@RequestMapping("/api/urls/{shortKey}/stats")
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @GetMapping()
    public UrlStatsResponse getLinkStats(@PathVariable String shortKey) {
        return statsService.getUrlStats(shortKey);
    }
}
