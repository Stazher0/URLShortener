package zhulikov.project.urlshortener.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class UrlRequest {
    @NotNull(message = "URL must not be null")
    @NotBlank(message = "URL must not be empty.")
    @URL(message = "invalid URL")
    private String originalUrl;
}
