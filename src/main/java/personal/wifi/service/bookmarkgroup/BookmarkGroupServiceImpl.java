package personal.wifi.service.bookmarkgroup;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.wifi.dto.bookmarkgroup.BookmarkGroupRequestDto;
import personal.wifi.dto.bookmarkgroup.BookmarkGroupResponseDto;
import personal.wifi.entity.bookmarkgroup.BookmarkGroup;
import personal.wifi.exception.TryToSaveDuplicateNameException;
import personal.wifi.repository.bookmarkgroup.BookmarkGroupRepository;

import javax.persistence.EntityNotFoundException;
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
    public void validateDuplicateBookmarkGroupName(String bookmarkGroupName) {

        if (bookmarkGroupRepository.findByName(bookmarkGroupName).isPresent()) {
            throw new TryToSaveDuplicateNameException
                    ("같은 이름의 북마크 그룹이 이미 존재합니다. (등록된 북마크 그룹 이름: " + bookmarkGroupName + ")");
        }

    }

    @Override
    public BookmarkGroupResponseDto getBookmarkGroup(Long id) {

        BookmarkGroup bookmarkGroup = bookmarkGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("해당 북마크 그룹이 존재하지 않습니다. (bookmarkGroupId: " + id + ")"));

        BookmarkGroupResponseDto bookmarkGroupResponseDto
                = modelMapper.map(bookmarkGroup, BookmarkGroupResponseDto.class);

        bookmarkGroupResponseDto.setBookmarkCount(bookmarkGroup.getBookmarkList().size());

        return bookmarkGroupResponseDto;

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

}
