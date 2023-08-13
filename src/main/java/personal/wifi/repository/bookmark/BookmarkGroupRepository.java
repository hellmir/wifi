package personal.wifi.repository.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.wifi.entity.bookmark.BookmarkGroup;

public interface BookmarkGroupRepository extends JpaRepository<BookmarkGroup, Long> {
}
