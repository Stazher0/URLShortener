package zhulikov.project.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhulikov.project.urlshortener.model.Url;

import java.util.UUID;

public interface UrlRepo extends JpaRepository<Url, UUID> {

}
