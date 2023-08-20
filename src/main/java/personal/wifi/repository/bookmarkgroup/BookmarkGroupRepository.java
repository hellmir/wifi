package personal.wifi.repository.bookmarkgroup;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.wifi.entity.bookmarkgroup.BookmarkGroup;

import java.util.Optional;

public interface BookmarkGroupRepository extends JpaRepository<BookmarkGroup, Long> {
        Optional<BookmarkGroup> findByName(String bookmarkGroupName);
}
