package personal.wifi.entity.history;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private LocalDateTime viewedTime;

    private History(Double latitude, Double longitude, LocalDateTime viewedTime) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.viewedTime = viewedTime;

    }

    public static History createHistory(Double latitude, Double longitude) {
        return new History(latitude, longitude, LocalDateTime.now());
    }

}
