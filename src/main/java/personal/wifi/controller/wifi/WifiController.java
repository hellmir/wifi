package personal.wifi.controller.wifi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import personal.wifi.dto.bookmark_group.BookmarkGroupResponseDto;
import personal.wifi.dto.wifi.WifiDataResponseDto;
import personal.wifi.service.bookmark.BookmarkGroupService;
import personal.wifi.service.history.HistoryService;
import personal.wifi.service.wifi.WifiService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WifiController {

    private final WifiService wifiService;
    private final HistoryService historyService;
    private final BookmarkGroupService bookmarkGroupService;

    @GetMapping("wifi")
    public ResponseEntity<List<WifiDataResponseDto>> getTwentyWifisAroundCurrentLocation
            (@RequestParam("latitude") Double myLAT, @RequestParam("longitude") Double myLNT) {

        List<WifiDataResponseDto> wifiDataResponseDtoList = wifiService.getNearTwentyWifis(myLAT, myLNT);

        historyService.saveHistory(myLAT, myLNT);

        return ResponseEntity.status(HttpStatus.OK).body(wifiDataResponseDtoList);

    }

    @GetMapping("detail")
    public String getWifiDetail(@RequestParam("mgrNo") String managementNo, Model model) {

        WifiDataResponseDto wifiDataResponseDto = wifiService.getWifiDetail(managementNo);
        model.addAttribute("wifi", wifiDataResponseDto);

        List<BookmarkGroupResponseDto> bookmarkGroups = bookmarkGroupService.getAllBookmarkGroups();
        model.addAttribute("bookmarkGroups", bookmarkGroups);

        return "wifi/detail";

    }

}
