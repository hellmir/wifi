package personal.wifi.service.bookmark_group;

import personal.wifi.dto.bookmark_group.BookmarkGroupRequestDto;
import personal.wifi.dto.bookmark_group.BookmarkGroupResponseDto;

import java.util.List;

public interface BookmarkGroupService {

    List<BookmarkGroupResponseDto> getAllBookmarkGroups();

    BookmarkGroupResponseDto saveBookmarkGroup(BookmarkGroupRequestDto bookmarkGroupRequestDto);

    void validateDuplicateBookmarkGroupName(String bookmarkGroupName);

    BookmarkGroupResponseDto getBookmarkGroup(Long id);

    BookmarkGroupResponseDto updateBookmarkGroup(Long id, BookmarkGroupRequestDto bookmarkGroupRequestDto);

    void deleteBookmarkGroup(Long id);

}
