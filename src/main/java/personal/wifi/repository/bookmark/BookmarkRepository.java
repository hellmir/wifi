package personal.wifi.repository.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.wifi.entity.bookmark.Bookmark;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findAllByOrderByBookmarkGroupIdAsc();
}
