package zhulikov.project.urlshortener.component;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimitFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    private Bucket createNewBucket() {
        //раз в минуту давать 5 запросов
        Bandwidth limit = Bandwidth.builder()
                .capacity(5)
                .refillGreedy(5, Duration.ofMinutes(1))
                .build();

        return Bucket.builder().addLimit(limit).build();
    }

    private Bucket resolveBucket(String ip){
        return cache.computeIfAbsent(ip,_ -> createNewBucket());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        //перехватываем get и post запросы ссылке /api/urls
        if (request.getRequestURI().equals("/api/urls") && request.getMethod().equals("POST")) {

            String ip = request.getRemoteAddr();

            Bucket bucket = resolveBucket(ip);

            if(bucket.tryConsume(1)){
                filterChain.doFilter(request,response);
            }else {
                // Если токенов нет, возвращаем ошибку 429.
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Too many requests. Please try again later.\"}");
            }
        } else {
            // Если это не эндпоинт создания ссылок, пропускаем запрос без проверки лимита.
            filterChain.doFilter(request, response);
        }

    }


}
