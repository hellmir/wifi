package personal.wifi.controller.bookmark;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import personal.wifi.dto.bookmark.BookmarkRequestDto;
import personal.wifi.dto.bookmark.BookmarkResponseDto;
import personal.wifi.service.bookmark.BookmarkService;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping("bookmark-list")
    public String getAllBookmarkList(Model model) {

        List<BookmarkResponseDto> bookmarkResponseDtoList = bookmarkService.getAllBookmarkList();

        model.addAttribute("bookmarkList", bookmarkResponseDtoList);

        return "bookmark/bookmark-list";

    }

    // SQLite는 lock 기법을 사용해 동시성을 관리하므로, 데이터베이스 읽기와 쓰기 작업을 컨트롤러에서 분리
    @PostMapping("bookmark-add")
    public ResponseEntity<BookmarkResponseDto> addBookmarkInBookmarkGroup
            (@RequestBody BookmarkRequestDto bookmarkRequestDto) {

        bookmarkService.createBookmark(bookmarkRequestDto);

        BookmarkResponseDto bookmarkResponseDto = bookmarkService.saveBookmark();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookmarkResponseDto.getBookmarkId())
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookmarkResponseDto);

    }

    @GetMapping("bookmark-delete")
    public String renderBookmarkDelete(Model model, @RequestParam Long id) {

        BookmarkResponseDto bookmarkResponseDto = bookmarkService.getBookmark(id);

        model.addAttribute("id", id);
        model.addAttribute("bookmarkGroupName", bookmarkResponseDto.getBookmarkGroupName());
        model.addAttribute("wifiMainName", bookmarkResponseDto.getWifiMainName());
        model.addAttribute("registeredTime", bookmarkResponseDto.getRegisteredTime());

        return "bookmark/bookmark-delete";

    }

    @DeleteMapping("bookmark-delete")
    public ResponseEntity<Void> deleteBookmark(@RequestParam Long id) {

        bookmarkService.deleteBookmark(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
