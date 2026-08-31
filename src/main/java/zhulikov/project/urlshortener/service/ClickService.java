package zhulikov.project.urlshortener.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zhulikov.project.urlshortener.model.Click;
import zhulikov.project.urlshortener.model.Url;
import zhulikov.project.urlshortener.repository.ClickRepo;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ClickService {

    private final ClickRepo clickRepo;

    public void createClickData(Url urlModel) {
        Click clickModel = new Click();

        clickModel.setUrl(urlModel);
        clickModel.setClickedAt(LocalDateTime.now());

        clickRepo.save(clickModel);
    }
}
