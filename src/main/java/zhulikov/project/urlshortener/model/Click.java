package zhulikov.project.urlshortener.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
public class Click {

    @Id
    @SequenceGenerator(name = "link_seq",
            sequenceName = "link_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "link_seq")
    private Long clickId;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Url url;

    @CreatedDate
    private LocalDateTime clickedAt;

}
