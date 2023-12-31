package personal.wifi.dto.bookmarkgroup;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookmarkGroupResponseDto {

    private Long id;
    private String name;
    private Integer sequence;
    private int bookmarkCount;
    private LocalDateTime registeredTime;
    private LocalDateTime updatedTime;

}
