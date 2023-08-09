package personal.wifi.service.wifi;

import org.springframework.stereotype.Service;
import personal.wifi.dto.wifi.WifiDataDto;

import java.util.List;

@Service
public interface WifiService {
    Integer saveWifiInformation(List<WifiDataDto> wifiDataDtoList);
}
