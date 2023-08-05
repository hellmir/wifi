package personal.wifi.service.wifi;

import org.springframework.stereotype.Service;
import personal.wifi.dto.wifi.WifiDataDto;
import personal.wifi.dto.wifi.WifiResponseDto;

import java.util.List;

@Service
public interface WifiService {
    WifiResponseDto saveWifiInformation(List<WifiDataDto> wifiDataDtoList);

}
