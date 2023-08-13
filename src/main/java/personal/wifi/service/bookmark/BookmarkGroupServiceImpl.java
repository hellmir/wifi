package personal.wifi.service.bookmark;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import personal.wifi.dto.bookmark_group.BookmarkGroupRequestDto;
import personal.wifi.dto.bookmark_group.BookmarkGroupResponseDto;
import personal.wifi.entity.bookmark.BookmarkGroup;
import personal.wifi.repository.bookmark.BookmarkGroupRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkGroupServiceImpl implements BookmarkGroupService {

    private final BookmarkGroupRepository bookmarkGroupRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BookmarkGroupResponseDto> getAllBookmarkGroups() {

        List<BookmarkGroup> bookmarkGroups = bookmarkGroupRepository.findAll();

        return bookmarkGroups.stream()
                .map(bookmarkGroup -> modelMapper.map(bookmarkGroup, BookmarkGroupResponseDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public BookmarkGroupResponseDto saveBookmarkGroup(BookmarkGroupRequestDto bookmarkGroupRequestDto) {

        BookmarkGroup bookmarkGroup = BookmarkGroup.createBookmarkGroup
                (bookmarkGroupRequestDto.getName(), bookmarkGroupRequestDto.getSequence());

        BookmarkGroup savedBookmarkGroup = bookmarkGroupRepository.save(bookmarkGroup);

        return modelMapper.map(savedBookmarkGroup, BookmarkGroupResponseDto.class);

    }

    @Override
    public BookmarkGroupResponseDto updateBookmarkGroup
            (Long id, BookmarkGroupRequestDto bookmarkGroupRequestDto) {

        BookmarkGroup bookmarkGroup = bookmarkGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("해당 북마크 그룹이 존재하지 않습니다. (bookmarkGroupId: " + id + "."));

        bookmarkGroup.updateBookmarkGroup(bookmarkGroupRequestDto.getName(), bookmarkGroupRequestDto.getSequence());

        BookmarkGroup updatedBookmarkGroup = bookmarkGroupRepository.save(bookmarkGroup);

        return modelMapper.map(updatedBookmarkGroup, BookmarkGroupResponseDto.class);

    }

    @Override
    public void deleteBookmarkGroup(Long id) {

        bookmarkGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("해당 북마크 그룹이 존재하지 않습니다. (bookmarkGroupId: " + id + ")"));

        bookmarkGroupRepository.deleteById(id);

    }

    @Override
    public BookmarkGroupResponseDto getBookmarkGroup(Long id) {

        BookmarkGroup bookmarkGroup = bookmarkGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("해당 북마크 그룹이 존재하지 않습니다. (bookmarkGroupId: " + id + ")"));

        return modelMapper.map(bookmarkGroup, BookmarkGroupResponseDto.class);
    }

}
