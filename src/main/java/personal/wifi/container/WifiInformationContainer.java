package personal.wifi.container;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import personal.wifi.dto.wifi.WifiDataRequestDto;

import java.util.List;
import java.util.Map;

@Data
public class WifiInformationContainer {

    @JsonProperty("list_total_count")
    private Integer list_total_count;

    @JsonProperty("RESULT")
    private Map<String, String> result;

    @JsonProperty("row")
    private List<WifiDataRequestDto> wifiData;

}
