package personal.wifi.controller.wifi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.wifi.dto.wifi.WifiDataResponseDto;
import personal.wifi.service.wifi.WifiService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("wifis")
@RequiredArgsConstructor
public class WifiController {

    private final WifiService wifiService;

    @PostMapping("locations")
    public ResponseEntity<Void> saveLocation
            (HttpSession session, @RequestParam("latitude") double latitude,
             @RequestParam("longitude") double longitude) {

        session.setAttribute("latitude", latitude);
        session.setAttribute("longitude", longitude);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping()
    public ResponseEntity<List<WifiDataResponseDto>> getTwentyWifisAroundCurrentLocation(HttpSession session) {

        Object latitudeObj = session.getAttribute("latitude");
        Object longitudeObj = session.getAttribute("longitude");

        if (latitudeObj == null || longitudeObj == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        double myLAT = (Double) latitudeObj;
        double myLNT = (Double) longitudeObj;

        List<WifiDataResponseDto> wifiDataResponseDtoList = wifiService.getNearTwentyWifis(myLAT, myLNT);

        return ResponseEntity.status(HttpStatus.OK).body(wifiDataResponseDtoList);

    }

}
