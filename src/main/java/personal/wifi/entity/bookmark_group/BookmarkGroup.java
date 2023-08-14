package personal.wifi.entity.bookmark_group;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import personal.wifi.entity.bookmark.Bookmark;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "bookmark_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Global Sequence 방식
    @Column(name = "bookmark_group_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer sequence;

    @OneToMany(mappedBy = "bookmarkGroup", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Bookmark> bookmarkList;

    @Column(nullable = false, updatable = false)
    private LocalDateTime registeredTime;

    private LocalDateTime updatedTime;

    private BookmarkGroup(String name, Integer sequence) {

        this.name = name;
        this.sequence = sequence;
        registeredTime = LocalDateTime.now();

    }

    public static BookmarkGroup createBookmarkGroup(String name, Integer sequence) {
        return new BookmarkGroup(name, sequence);
    }

    public void updateBookmarkGroup(String name, Integer sequence) {

        this.name = name;
        this.sequence = sequence;
        updatedTime = LocalDateTime.now();

    }

}
