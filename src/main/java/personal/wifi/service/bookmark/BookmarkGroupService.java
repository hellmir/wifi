package personal.wifi.service.bookmark;

import personal.wifi.dto.bookmark_group.BookmarkGroupRequestDto;
import personal.wifi.dto.bookmark_group.BookmarkGroupResponseDto;
import personal.wifi.entity.bookmark.BookmarkGroup;

import java.util.List;

public interface BookmarkGroupService {

    List<BookmarkGroupResponseDto> getAllBookmarkGroups();
    BookmarkGroupResponseDto saveBookmarkGroup(BookmarkGroupRequestDto bookmarkGroupRequestDto);

    BookmarkGroupResponseDto updateBookmarkGroup(Long id, BookmarkGroupRequestDto bookmarkGroupRequestDto);

    void deleteBookmarkGroup(Long id);

    BookmarkGroupResponseDto getBookmarkGroup(Long id);

}
