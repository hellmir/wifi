package personal.wifi.repository.bookmark_group;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.wifi.entity.bookmark_group.BookmarkGroup;

import java.util.Optional;

public interface BookmarkGroupRepository extends JpaRepository<BookmarkGroup, Long> {
        Optional<BookmarkGroup> findByName(String bookmarkGroupName);
}
