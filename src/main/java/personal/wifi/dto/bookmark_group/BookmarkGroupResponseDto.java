package personal.wifi.dto.bookmark_group;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookmarkGroupResponseDto {

    private Long id;
    private String name;
    private Integer sequence;
    private LocalDateTime registeredTime;
    private LocalDateTime updatedTime;

}
