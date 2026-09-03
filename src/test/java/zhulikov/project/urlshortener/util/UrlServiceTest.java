package zhulikov.project.urlshortener.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zhulikov.project.urlshortener.model.Url;
import zhulikov.project.urlshortener.repository.UrlRepo;
import zhulikov.project.urlshortener.service.UrlService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrlServiceTest {

    @Mock
    private UrlRepo urlRepo;

    @InjectMocks
    private UrlService urlService;

    private final Long urlId = 12345L;

    @Test
    void createUrlModel_Success() {
        // Arrange
        when(urlRepo.save(any(Url.class))).thenAnswer(invocation -> {
            Url urlToSave = invocation.getArgument(0);
            if (urlToSave.getUrlId() == null) {
                urlToSave.setUrlId(urlId); // Устанавливаем ID при первом сохранении
            }
            return urlToSave;
        });

        // Act
        String originalUrl = "https://example.com";
        Url createdUrl = urlService.createUrlModel(originalUrl);

        // Assert
        assertNotNull(createdUrl);
        assertEquals(originalUrl, createdUrl.getOriginalUrl());
        assertNotNull(createdUrl.getShortKey());
        // Правильное значение Base62 для 12345
        String expectedShortKey = "3D7";
        assertEquals(expectedShortKey, createdUrl.getShortKey());
        assertNotNull(createdUrl.getCreatedDate());

        // Verify - save должен быть вызван 2 раза
        verify(urlRepo, times(2)).save(any(Url.class));
    }
}