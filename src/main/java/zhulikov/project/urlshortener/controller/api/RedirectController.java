package zhulikov.project.urlshortener.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import zhulikov.project.urlshortener.model.Url;
import zhulikov.project.urlshortener.repository.UrlRepo;
import zhulikov.project.urlshortener.service.ClickService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RedirectController {

    private final UrlRepo urlRepo;
    private final ClickService clickService;

    @GetMapping("/{shortKey}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortKey) {
        Url urlObject = urlRepo.findByShortKey(shortKey)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        clickService.createClickData(urlObject);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(urlObject.getOriginalUrl()))
                .build();
    }
}
