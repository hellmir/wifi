package personal.wifi.controller.bookmarkgroup;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import personal.wifi.dto.bookmarkgroup.BookmarkGroupRequestDto;
import personal.wifi.dto.bookmarkgroup.BookmarkGroupResponseDto;
import personal.wifi.service.bookmarkgroup.BookmarkGroupService;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookmarkGroupController {

    private final BookmarkGroupService bookmarkGroupService;

    @GetMapping("bookmark-group")
    public String getAllBookmarkGroups(Model model) {

        List<BookmarkGroupResponseDto> bookmarkGroupResponseDtoList = bookmarkGroupService.getAllBookmarkGroups();

        model.addAttribute("bookmarkGroups", bookmarkGroupResponseDtoList);

        return "bookmark-group/bookmark-group";

    }

    @GetMapping("bookmark-group-add")
    public String renderBookmarkGroupAdd() {
        return "bookmark-group/bookmark-group-add";
    }

    // SQLite는 lock 기법을 사용해 동시성을 관리하므로, 데이터베이스 읽기와 쓰기 작업을 컨트롤러에서 분리
    @PostMapping("bookmark-group-add")
    public ResponseEntity<BookmarkGroupResponseDto> createBookmarkGroup
            (@RequestBody BookmarkGroupRequestDto bookmarkGroupRequestDto) {

        bookmarkGroupService.validateDuplicateBookmarkGroupName(bookmarkGroupRequestDto.getName());

        BookmarkGroupResponseDto bookmarkGroupResponseDto
                = bookmarkGroupService.saveBookmarkGroup(bookmarkGroupRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookmarkGroupResponseDto.getId())
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookmarkGroupResponseDto);

    }

    @GetMapping("bookmark-group-edit")
    public String renderBookmarkGroupEdit(Model model, @RequestParam Long id) {

        BookmarkGroupResponseDto bookmarkGroupResponseDto = bookmarkGroupService.getBookmarkGroup(id);

        model.addAttribute("id", id);
        model.addAttribute("name", bookmarkGroupResponseDto.getName());
        model.addAttribute("sequence", bookmarkGroupResponseDto.getSequence());

        return "bookmark-group/bookmark-group-edit";

    }

    @PatchMapping("bookmark-group-edit")
    public ResponseEntity<BookmarkGroupResponseDto> updateBookmarkGroup
            (@RequestParam Long id, @RequestBody BookmarkGroupRequestDto bookmarkGroupRequestDto) {

        BookmarkGroupResponseDto bookmarkGroupResponseDto
                = bookmarkGroupService.updateBookmarkGroup(id, bookmarkGroupRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(bookmarkGroupResponseDto);

    }

    @GetMapping("bookmark-group-delete")
    public String renderBookmarkGroupDelete(Model model, @RequestParam Long id) {

        BookmarkGroupResponseDto bookmarkGroupResponseDto = bookmarkGroupService.getBookmarkGroup(id);

        model.addAttribute("id", id);
        model.addAttribute("name", bookmarkGroupResponseDto.getName());
        model.addAttribute("sequence", bookmarkGroupResponseDto.getSequence());
        model.addAttribute("bookmarkCount", bookmarkGroupResponseDto.getBookmarkCount());

        return "bookmark-group/bookmark-group-delete";

    }

    @DeleteMapping("bookmark-group-delete")
    public ResponseEntity<Void> deleteBookmarkGroup(@RequestParam Long id) {

        bookmarkGroupService.deleteBookmarkGroup(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
