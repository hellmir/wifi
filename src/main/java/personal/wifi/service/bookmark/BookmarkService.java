package personal.wifi.service.bookmark;

import personal.wifi.dto.bookmark.BookmarkRequestDto;
import personal.wifi.dto.bookmark.BookmarkResponseDto;

import java.util.List;

public interface BookmarkService {

    List<BookmarkResponseDto> getAllBookmarkList();

    BookmarkResponseDto getBookmark(Long id);

    void createBookmark(BookmarkRequestDto bookmarkRequestDto);

    BookmarkResponseDto saveBookmark();

    void deleteBookmark(Long id);
}
