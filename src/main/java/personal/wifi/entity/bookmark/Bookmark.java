package personal.wifi.entity.bookmark;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import personal.wifi.entity.bookmarkgroup.BookmarkGroup;
import personal.wifi.entity.wifi.WifiData;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "bookmark")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Global Sequence 방식
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookmark_group_id", nullable = false)
    private BookmarkGroup bookmarkGroup;

    @OneToOne
    @JoinColumn(name = "X_SWIFI_MGR_NO", nullable = false)
    private WifiData wifiData;

    @Column(nullable = false, updatable = false)
    private LocalDateTime registeredTime;

    private Bookmark(BookmarkGroup bookmarkGroup, WifiData wifiData) {

        this.bookmarkGroup = bookmarkGroup;
        this.wifiData = wifiData;
        registeredTime = LocalDateTime.now();

    }

    public static Bookmark createBookmark(BookmarkGroup bookmarkGroup, WifiData wifiData) {
        return new Bookmark(bookmarkGroup, wifiData);
    }

}
