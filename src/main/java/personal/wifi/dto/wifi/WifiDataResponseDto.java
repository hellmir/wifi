package personal.wifi.dto.wifi;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WifiDataResponseDto {

    private double distance; // 현재 위치와의 거리(km)
    private String managementNo; // 관리번호
    private String wardOffice; // 자치구
    private String mainName; // 와이파이명
    private String address1; // 도로명주소
    private String address2; // 상세주소
    private String installationFloor; // 설치위치(층)
    private String installationType; // 설치유형
    private String installationManufacturedBy; // 설치기관
    private String serviceSeparatedEntry; // 서비스구분
    private String CMCWR; // 망종류
    private String constructionYear; // 설치년도
    private String inoutDoor; // 실내외구분
    private String REMARS3; // wifi접속환경
    private Double latitude; // X좌표
    private Double longitude; // Y좌표
    private Timestamp workedDateTime; // 작업일자

}
