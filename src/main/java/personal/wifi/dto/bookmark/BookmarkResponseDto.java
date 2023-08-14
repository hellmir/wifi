package personal.wifi.dto.bookmark;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookmarkResponseDto {

    private Long bookmarkId;

    private Long bookmarkGroupId;
    private String bookmarkGroupName;

    private String wifiManagementNo;
    private String wifiMainName;

    private LocalDateTime registeredTime;

}
