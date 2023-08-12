package personal.wifi.controller.wifi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import personal.wifi.dto.wifi.WifiDataResponseDto;
import personal.wifi.service.history.HistoryService;
import personal.wifi.service.wifi.WifiService;

import java.util.List;

@RestController
@RequestMapping("wifis")
@RequiredArgsConstructor
public class WifiController {

    private final WifiService wifiService;
    private final HistoryService historyService;

    @GetMapping()
    public ResponseEntity<List<WifiDataResponseDto>> getTwentyWifisAroundCurrentLocation
            (@RequestParam("latitude") Double myLAT, @RequestParam("longitude") Double myLNT) {

        List<WifiDataResponseDto> wifiDataResponseDtoList = wifiService.getNearTwentyWifis(myLAT, myLNT);

        historyService.saveHistory(myLAT, myLNT);

        return ResponseEntity.status(HttpStatus.OK).body(wifiDataResponseDtoList);

    }

    @GetMapping("detail")
    public ResponseEntity<WifiDataResponseDto> getWifiDetail(@RequestParam("mgrNo") String managementNo) {

        WifiDataResponseDto wifiDataResponseDto = wifiService.getWifiDetail(managementNo);

        return ResponseEntity.status(HttpStatus.OK).body(wifiDataResponseDto);

    }

}
