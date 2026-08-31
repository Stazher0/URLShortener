package zhulikov.project.urlshortener.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import zhulikov.project.urlshortener.model.Url;
import zhulikov.project.urlshortener.repository.UrlRepo;
import zhulikov.project.urlshortener.util.Base62Utils;

@Service
@Transactional
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepo urlRepo;

    public Url createUrlModel(String originalUrl) {

        Url urlModel = new Url();

        urlModel.setCreatedDate(LocalDateTime.now());
        urlModel.setOriginalUrl(originalUrl);

        Url savedUrl = urlRepo.save(urlModel);

        String shortKey = shortUrl(savedUrl.getUrlId());
        savedUrl.setShortKey(shortKey);

        return urlRepo.save(savedUrl);
    }

    public String shortUrl(Long id){
        return Base62Utils.encode(id);
    }
}
