package personal.wifi.service.wifi;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.wifi.dto.wifi.WifiDataRequestDto;
import personal.wifi.dto.wifi.WifiDataResponseDto;
import personal.wifi.entity.wifi.WifiData;
import personal.wifi.repository.wifi.WifiRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WifiServiceImpl implements WifiService {

    private final WifiRepository wifiRepository;
    private final ModelMapper modelMapper;

    @Override
    public Integer saveWifiInformation(List<WifiDataRequestDto> wifiDataDtoList) {

        for (WifiDataRequestDto wifiDataDto : wifiDataDtoList) {

            WifiData wifiData = WifiData.builder()
                    .managementNo(wifiDataDto.getManagementNo())
                    .wardOffice(wifiDataDto.getWardOffice())
                    .mainName(wifiDataDto.getMainName())
                    .address1(wifiDataDto.getAddress1())
                    .address2(wifiDataDto.getAddress2())
                    .installationFloor(wifiDataDto.getInstallationFloor())
                    .installationType(wifiDataDto.getInstallationType())
                    .installationManufacturedBy(wifiDataDto.getInstallationManufacturedBy())
                    .serviceSeparatedEntry(wifiDataDto.getServiceSeparatedEntry())
                    .CMCWR(wifiDataDto.getCMCWR())
                    .constructionYear(wifiDataDto.getConstructionYear())
                    .inoutDoor(wifiDataDto.getInoutDoor())
                    .REMARS3(wifiDataDto.getREMARS3())
                    .latitude(wifiDataDto.getLatitude())
                    .longitude(wifiDataDto.getLongitude())
                    .workedDateTime(wifiDataDto.getWorkedDateTime())
                    .build();

            wifiRepository.save(wifiData);

        }

        List<WifiData> wifiData1 = wifiRepository.findAll();

        return wifiDataDtoList.size();

    }

    @Override
    public List<WifiDataResponseDto> getNearTwentyWifis(double myLAT, double myLNT) {

        List<WifiData> nearTwentyWifis = wifiRepository.findNearTwentyWifis(myLAT, myLNT);

        List<WifiDataResponseDto> wifiDataResponseDtoList = new ArrayList<>();

        for (WifiData nearWifi : nearTwentyWifis) {

            WifiDataResponseDto wifiDataResponseDto = modelMapper.map(nearWifi, WifiDataResponseDto.class);

/*            double distance = calculateDistance(myLAT, myLNT, nearWifi.getLatitude(), nearWifi.getLongitude());

            wifiDataResponseDto.setDistance(distance);*/

            wifiDataResponseDtoList.add(wifiDataResponseDto);

        }

        return wifiDataResponseDtoList;

    }

/*    private double calculateDistance(double myLAT, double myLNT, double targetLAT, double targetLNT) {
        final int R = 6371; // 지구의 반지름 (킬로미터 단위)

        double dLat = Math.toRadians(targetLAT - myLAT);
        double dLon = Math.toRadians(targetLNT - myLNT);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(myLAT)) * Math.cos(Math.toRadians(targetLAT))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // 결과는 킬로미터 단위
    }*/


/*    private WifiDataResponseDto setWifiDataResponseDto(List<WifiData> nearTwentyWifis) {

        WifiDataResponseDto wifiDataResponseDto = new WifiDataResponseDto();

        wifiDataResponseDto.setWifiDataList(nearTwentyWifis);

        return wifiDataResponseDto;

    }*/

/*    private double calculateDistance(double myLAT, double myLNT, double targetLAT, double targetLNT) {

        final int R = 6371; // 지구 반지름(km)

        double dLat = Math.toRadians(myLAT - targetLAT);
        double dLon = Math.toRadians(myLNT - targetLNT);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(myLAT)) * Math.cos(Math.toRadians(targetLAT))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // 거리(km)
    }*/


}
