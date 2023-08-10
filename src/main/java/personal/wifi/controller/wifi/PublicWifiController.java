package personal.wifi.controller.wifi;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import personal.wifi.dto.external_api.WifiApiResponseDto;
import personal.wifi.dto.wifi.WifiDataRequestDto;
import personal.wifi.service.wifi.WifiService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PublicWifiController {

    private final WifiService wifiService;

    private static final Logger logger = LoggerFactory.getLogger(WifiController.class);

    private WebClient webClient;

    @Value("${wifi-service.host}")
    private String wifiServiceHost;

    @Value("${wifi-service.port}")
    private int wifiServicePort;

    @Value("${wifi-service.api-key}")
    private String wifiServiceApiKey;

    @Value("${wifi-service.first-index}")
    int firstIndex;

    @Value("${wifi-service.last-index}")
    int lastIndex;

    @RequestMapping("load-wifi")
    public String receiveWifiInformation(Model model) {

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024)) // 예: 1MB
                .build();

        this.webClient = WebClient.builder()
                .exchangeStrategies(exchangeStrategies)
                .build();

        List<WifiDataRequestDto> wifiDataDtoList = getResponseFlux();

        validateWifiDataDtoListIsNull(wifiDataDtoList);

        Integer wifiCount = wifiService.saveWifiInformation(wifiDataDtoList);

        model.addAttribute("wifiCount", wifiCount);

        return "load-wifi";

    }

    private List<WifiDataRequestDto> getResponseFlux() {

        List<WifiDataRequestDto> allWifiData = new ArrayList<>();

        while (true) {

            WifiApiResponseDto response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("http")
                            .host(wifiServiceHost)
                            .port(wifiServicePort)
                            .path(wifiServiceApiKey + "/json/TbPublicWifiInfo/" + firstIndex + "/" + lastIndex)
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(WifiApiResponseDto.class)
                    .block();

            if (response.getWifiInformationContainer() == null) {
                break;
            }

            List<WifiDataRequestDto> wifiDataDtoList = response.getWifiInformationContainer().getWifiData();

            allWifiData.addAll(wifiDataDtoList);

            firstIndex += 1_000;
            lastIndex += 1_000;

        }

        firstIndex = 1;
        lastIndex = 1_000;

        return allWifiData;

    }

    private void validateWifiDataDtoListIsNull(List<WifiDataRequestDto> wifiDataDtoList) {

        if (wifiDataDtoList == null) {
            logger.error("WebClient에서 데이터를 받아 오지 못했습니다:");
            throw new NullPointerException("JSON 파일의 역직렬화에 실패하여 wifiDataDtoList에 객체를 받아 오지 못했습니다.");
        }

    }

    private ResponseEntity<Integer> buildResponse
            (Integer wifiCount) {

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/load-wifi")
                .buildAndExpand(wifiCount)
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity.created(location).headers(headers).body(wifiCount);

    }

}
