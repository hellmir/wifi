package personal.wifi.entity.bookmark;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import personal.wifi.entity.bookmarkgroup.BookmarkGroup;
import personal.wifi.entity.wifi.WifiData;

import javax.persistence.*;
import java.time.LocalDateTime;

// 하나의 북마크에 여러 와이파이를 추가할 수도 있으므로, 북마크 그룹과 따로 엔티티로 등록하고 독자적인 index 사용
// 북마크 등록시간은 WifiData가 가지는 속성이 아니기 때문에, 북마크 그룹을 WifiData가 아니라 별도의 Bookmark 엔티티와 mapping
@Getter
@Entity
@Table(name = "bookmark")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Global Sequence 방식
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bookmark_group_id", nullable = false)
    private BookmarkGroup bookmarkGroup;

    @OneToOne
    @JoinColumn(name = "X_SWIFI_MGR_NO", nullable = false)
    private WifiData wifiData;

    @Column(nullable = false, updatable = false)
    private LocalDateTime registeredTime;

    private Bookmark(BookmarkGroup bookmarkGroup, WifiData wifiData) {

        this.bookmarkGroup = bookmarkGroup;

        bookmarkGroup.getBookmarkList().add(this);

        this.wifiData = wifiData;
        registeredTime = LocalDateTime.now();

    }

    public static Bookmark createBookmark(BookmarkGroup bookmarkGroup, WifiData wifiData) {
        return new Bookmark(bookmarkGroup, wifiData);
    }

}
