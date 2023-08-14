package personal.wifi.service.bookmark;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal.wifi.dto.bookmark.BookmarkRequestDto;
import personal.wifi.dto.bookmark.BookmarkResponseDto;
import personal.wifi.entity.bookmark.Bookmark;
import personal.wifi.entity.bookmark_group.BookmarkGroup;
import personal.wifi.entity.wifi.WifiData;
import personal.wifi.repository.bookmark.BookmarkRepository;
import personal.wifi.repository.bookmark_group.BookmarkGroupRepository;
import personal.wifi.repository.wifi.WifiRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkGroupRepository bookmarkGroupRepository;
    private final WifiRepository wifiRepository;
    private final BookmarkRepository bookmarkRepository;

    private Bookmark bookmark;

    @Override
    public List<BookmarkResponseDto> getAllBookmarkList() {

        // 북마크 목록을 북마크 그룹 ID 순으로 정렬
        List<Bookmark> bookmarkList = bookmarkRepository.findAllByOrderByBookmarkGroupIdAsc();

        List<BookmarkResponseDto> bookmarkResponseDtoList = new ArrayList<>();

        for (Bookmark bookmark : bookmarkList) {
            bookmarkResponseDtoList.add(setBookmarkResponseDto(bookmark));
        }

        return bookmarkResponseDtoList;

    }

    @Override
    public void createBookmark(BookmarkRequestDto bookmarkRequestDto) {

        BookmarkGroup bookmarkGroup = bookmarkGroupRepository.findByName(bookmarkRequestDto.getBookmarkGroupName())
                .orElseThrow(() -> new EntityNotFoundException
                        ("일치하는 북마크 그룹이 존재하지 않습니다. (bookmarkGroupName: "
                                + bookmarkRequestDto.getBookmarkGroupName() + ")"));

        WifiData wifiData = wifiRepository.findByManagementNo(bookmarkRequestDto.getManagementNo())
                .orElseThrow(() -> new EntityNotFoundException
                        ("일치하는 와이파이 데이터가 존재하지 않습니다. (managementNo: "
                                + bookmarkRequestDto.getManagementNo() + ")"));

        bookmark = Bookmark.createBookmark(bookmarkGroup, wifiData);

    }

    @Override
    public BookmarkResponseDto saveBookmark() {

        Bookmark savedBookmark = bookmarkRepository.save(bookmark);

        return setBookmarkResponseDto(savedBookmark);

    }

    @Override
    public BookmarkResponseDto getBookmark(Long id) {

        Bookmark bookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("해당 북마크가 존재하지 않습니다. (bookmarkId: " + id + "."));

        return setBookmarkResponseDto(bookmark);

    }

    @Override
    public void deleteBookmark(Long id) {

        bookmarkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("해당 북마크가 존재하지 않습니다. (bookmarkId: " + id + "."));

        bookmarkRepository.deleteById(id);

    }


    private BookmarkResponseDto setBookmarkResponseDto(Bookmark bookmark) {

        BookmarkResponseDto bookmarkResponseDto = new BookmarkResponseDto();

        bookmarkResponseDto.setBookmarkId(bookmark.getId());

        bookmarkResponseDto.setBookmarkGroupId(bookmark.getBookmarkGroup().getId());
        bookmarkResponseDto.setBookmarkGroupName(bookmark.getBookmarkGroup().getName());

        bookmarkResponseDto.setWifiManagementNo(bookmark.getWifiData().getManagementNo());
        bookmarkResponseDto.setWifiMainName(bookmark.getWifiData().getMainName());

        bookmarkResponseDto.setRegisteredTime(bookmark.getRegisteredTime());

        return bookmarkResponseDto;

    }

}
