package personal.wifi.service.wifi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.wifi.dto.wifi.WifiDataDto;
import personal.wifi.entity.wifi.WifiData;
import personal.wifi.repository.wifi.WifiRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WifiServiceImpl implements WifiService {

    private final WifiRepository wifiRepository;

    private Integer wifiCount;

    @Override
    public Integer saveWifiInformation(List<WifiDataDto> wifiDataDtoList) {

        wifiCount = 0;

        for (WifiDataDto wifiDataDto : wifiDataDtoList) {

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

            ++wifiCount;

        }

        return wifiCount;

    }

}
