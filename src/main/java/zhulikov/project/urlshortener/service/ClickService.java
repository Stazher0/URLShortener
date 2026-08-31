package zhulikov.project.urlshortener.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhulikov.project.urlshortener.model.Click;
import zhulikov.project.urlshortener.model.Url;
import zhulikov.project.urlshortener.repository.ClickRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@Transactional
@RequiredArgsConstructor
public class ClickService {

    private final ClickRepo clickRepo;

    private final BlockingQueue <Click> clickQueue =  new LinkedBlockingQueue<>();

    public void createClickData(Url urlModel) {
        Click clickModel = new Click();

        clickModel.setUrl(urlModel);
        clickModel.setClickedAt(LocalDateTime.now());

        addClickToQueue(clickModel);
    }

    public void addClickToQueue(Click click) {
        clickQueue.offer(click);
    }

    @Scheduled(fixedDelay = 5000)
    public void sendClickData(){
        List<Click> clicks = new ArrayList<>();
        clickQueue.drainTo(clicks);

        if (!clicks.isEmpty()){
            clickRepo.saveAll(clicks);
            System.out.println("Saved " + clicks.size() + " clicks");
        }
    }
}
