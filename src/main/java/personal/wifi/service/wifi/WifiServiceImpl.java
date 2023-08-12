package personal.wifi.service.wifi;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.wifi.dto.wifi.WifiDataRequestDto;
import personal.wifi.dto.wifi.WifiDataResponseDto;
import personal.wifi.entity.wifi.WifiData;
import personal.wifi.repository.wifi.WifiRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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

        return wifiDataDtoList.size();

    }

    @Override
    public List<WifiDataResponseDto> getNearTwentyWifis(double myLAT, double myLNT) {

        List<WifiData> nearTwentyWifis = wifiRepository.findNearTwentyWifis(myLAT, myLNT);

        if (nearTwentyWifis.isEmpty()) {
            throw new EntityNotFoundException("저장된 와이파이 데이터가 없습니다.");
        }

        return nearTwentyWifis.stream()
                .map(wifiData -> modelMapper.map(wifiData, WifiDataResponseDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public WifiDataResponseDto getWifiDetail(String managementNo) {

        WifiData wifiData = wifiRepository.findByManagementNo(managementNo)
                .orElseThrow(() -> new EntityNotFoundException
                        ("입력된 관리번호와 일치하는 와이파이 데이터가 없습니다. (managementNo: " + managementNo + ")"));

        return modelMapper.map(wifiData, WifiDataResponseDto.class);
    }

}
