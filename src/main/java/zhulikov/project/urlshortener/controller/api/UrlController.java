package zhulikov.project.urlshortener.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhulikov.project.urlshortener.model.Url;
import zhulikov.project.urlshortener.model.UrlRequest;
import zhulikov.project.urlshortener.service.UrlService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    @PostMapping()
    public ResponseEntity<Url> saveUrlModel(@RequestBody UrlRequest request){
        Url savedUrl = urlService.createUrlModel(request.getOriginalUrl());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUrl);
    }
}