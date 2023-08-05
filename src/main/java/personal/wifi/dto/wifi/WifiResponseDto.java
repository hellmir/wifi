package personal.wifi.dto.wifi;

import lombok.Data;
import personal.wifi.entity.wifi.WifiData;

import java.util.List;

@Data
public class WifiResponseDto {
    List<WifiData> wifiDataList;
}
