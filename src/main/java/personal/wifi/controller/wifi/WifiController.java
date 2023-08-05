package personal.wifi.controller.wifi;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import personal.wifi.container.WifiInformationContainer;
import personal.wifi.dto.external_api.WifiApiResponseDto;
import personal.wifi.dto.wifi.WifiDataDto;
import personal.wifi.dto.wifi.WifiResponseDto;
import personal.wifi.service.wifi.WifiService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("wifis")
@RequiredArgsConstructor
public class WifiController {

    private final WifiService wifiService;

    private static final Logger logger = LoggerFactory.getLogger(WifiController.class);

    @Value("${wifi-service.host}")
    private String wifiServiceHost;

    @Value("${wifi-service.port}")
    private int wifiServicePort;

    @Value("${wifi-service.api-key}")
    private String wifiServiceApiKey;

    @Value("${wifi-service.reception-count}")
    private String receptionCount;

    private final WebClient webClient = WebClient.create();

    @PostMapping
    public ResponseEntity<WifiResponseDto> receiveWifiInformation() {

        List<WifiDataDto> wifiDataDtoList = getResponseFlux();

        validateWifiDataDtoListIsNull(wifiDataDtoList);

        WifiResponseDto wifiResponseDto = wifiService.saveWifiInformation(wifiDataDtoList);

        return buildResponse(wifiResponseDto);

    }

    private List<WifiDataDto> getResponseFlux() {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host(wifiServiceHost)
                        .port(wifiServicePort)
                        .path(wifiServiceApiKey + "/json/TbPublicWifiInfo/1/" + receptionCount)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(WifiApiResponseDto.class)
                .doOnNext(wifiRequestDto -> logger.info("wifiRequestDto: " + wifiRequestDto))
                .map(WifiApiResponseDto::getWifiInformationContainer)
                .map(WifiInformationContainer::getWifiData)
                .block();

    }

    private void validateWifiDataDtoListIsNull(List<WifiDataDto> wifiDataDtoList) {

        if (wifiDataDtoList == null) {
            logger.error("WebClient에서 데이터를 받아 오지 못했습니다:");
            throw new NullPointerException("JSON 파일의 역직렬화에 실패하여 wifiDataDtoList에 객체를 받아 오지 못했습니다.");
        }

    }

    private ResponseEntity<WifiResponseDto> buildResponse
            (WifiResponseDto wifiResponseDto) {

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/wifi-informations")
                .buildAndExpand(wifiResponseDto)
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity.created(location).headers(headers).body(wifiResponseDto);

    }

}
