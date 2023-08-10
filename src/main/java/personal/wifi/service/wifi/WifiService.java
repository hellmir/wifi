package personal.wifi.service.wifi;

import org.springframework.stereotype.Service;
import personal.wifi.dto.wifi.WifiDataRequestDto;
import personal.wifi.dto.wifi.WifiDataResponseDto;

import java.util.List;

@Service
public interface WifiService {
    Integer saveWifiInformation(List<WifiDataRequestDto> wifiDataDtoList);

    List<WifiDataResponseDto> getNearTwentyWifis(double myLAT, double myLNT);
}
