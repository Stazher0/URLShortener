package zhulikov.project.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhulikov.project.urlshortener.model.Click;
import zhulikov.project.urlshortener.model.Url;

public interface ClickRepo extends JpaRepository<Click,Long> {
    long countByUrl(Url url);
}
