package zhulikov.project.urlshortener.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Url {

    @Id
    @SequenceGenerator(name = "link_seq",
            sequenceName = "link_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "link_seq")
    private Long id;

    @Column(unique = true)
    private String shortKey;

    private String originalUrl;

    @CreatedDate
    private LocalDateTime createdDate;
}