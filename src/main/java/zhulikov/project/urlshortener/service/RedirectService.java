package zhulikov.project.urlshortener.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import zhulikov.project.urlshortener.model.Url;
import zhulikov.project.urlshortener.repository.UrlRepo;

import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedirectService {

    private final UrlRepo urlRepo;
    private final ClickService clickService;
    private final CacheService cacheService;

    public ResponseEntity<Void> getOriginalUrlForRedirect(String shortKey){

        Optional<String> cachedUrl = findOriginalUrlInCache(shortKey);

        if (cachedUrl.isPresent()) {
            Url url = findOriginalUrlInDB(shortKey);
            clickService.createClickData(url);

            return buildRedirectResponse(cachedUrl.get());
        }

        Url url = findOriginalUrlInDB(shortKey);

        clickService.createClickData(url);

        cacheService.saveToCache(shortKey, url.getOriginalUrl());

        return buildRedirectResponse(url.getOriginalUrl());
    }

    public Url findOriginalUrlInDB(String shortKey){
        return urlRepo.findByShortKey(shortKey)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Link not found: " + shortKey
                ));
    }

    public Optional<String> findOriginalUrlInCache(String shortKey){
        return cacheService.getFromCache(shortKey);
    }

    public ResponseEntity<Void> buildRedirectResponse(String originalUrl){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
