package zhulikov.project.urlshortener.controller.api;

import cn.hutool.core.codec.Base62;
import cn.hutool.core.codec.Base62Codec;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhulikov.project.urlshortener.model.Url;
import zhulikov.project.urlshortener.service.UrlService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    @PostMapping()
    public ResponseEntity<Url> saveCreatedUrl(@RequestBody Url url){
        String shortKey = UUID.randomUUID().toString();
        Url savedUrl = urlService.saveUrl(shortKey);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUrl);
    }
}