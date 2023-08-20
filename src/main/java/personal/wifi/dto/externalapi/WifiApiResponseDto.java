package personal.wifi.dto.externalapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import personal.wifi.container.WifiInformationContainer;

@Data
public class WifiApiResponseDto {

    @JsonProperty("TbPublicWifiInfo")
    private WifiInformationContainer wifiInformationContainer;

}
