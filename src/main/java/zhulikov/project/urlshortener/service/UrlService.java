package zhulikov.project.urlshortener.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import zhulikov.project.urlshortener.model.Url;
import zhulikov.project.urlshortener.repository.UrlRepo;

@Service
@Transactional
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepo urlRepo;

    public Url saveUrl(String shortKey) {
        Url url = new Url();
        url.setShortKey(shortKey);
        url.setCreatedDate(LocalDateTime.now());

        return urlRepo.save(url);
    }


}
