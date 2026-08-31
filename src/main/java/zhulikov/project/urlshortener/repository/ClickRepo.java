package zhulikov.project.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhulikov.project.urlshortener.model.Click;

public interface ClickRepo extends JpaRepository<Click,Long> {
}
