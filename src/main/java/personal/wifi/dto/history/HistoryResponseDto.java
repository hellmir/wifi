package personal.wifi.dto.history;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoryResponseDto {

    private Long id;
    private Double latitude;
    private Double longitude;
    private LocalDateTime viewedTime;

}
