package zhulikov.project.urlshortener.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import zhulikov.project.urlshortener.model.Url;
import zhulikov.project.urlshortener.repository.UrlRepo;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class RedirectService {

    private final UrlRepo urlRepo;
    private final ClickService clickService;

    public ResponseEntity<Void> getOriginalUrlForRedirect(String shortKey){
        Url url = urlRepo
                .findByShortKey(shortKey)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        clickService.createClickData(url);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(url.getOriginalUrl()))
                .build();
    }
}
