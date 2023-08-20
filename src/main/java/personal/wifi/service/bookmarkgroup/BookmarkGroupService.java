package personal.wifi.service.bookmarkgroup;

import personal.wifi.dto.bookmarkgroup.BookmarkGroupRequestDto;
import personal.wifi.dto.bookmarkgroup.BookmarkGroupResponseDto;

import java.util.List;

public interface BookmarkGroupService {

    List<BookmarkGroupResponseDto> getAllBookmarkGroups();

    BookmarkGroupResponseDto saveBookmarkGroup(BookmarkGroupRequestDto bookmarkGroupRequestDto);

    void validateDuplicateBookmarkGroupName(String bookmarkGroupName);

    BookmarkGroupResponseDto getBookmarkGroup(Long id);

    BookmarkGroupResponseDto updateBookmarkGroup(Long id, BookmarkGroupRequestDto bookmarkGroupRequestDto);

    void deleteBookmarkGroup(Long id);

}
