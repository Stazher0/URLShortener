package zhulikov.project.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhulikov.project.urlshortener.model.Url;

import java.util.Optional;

public interface UrlRepo extends JpaRepository<Url, Long> {
    Optional<Url> findByShortKey(String shortKey);
}
