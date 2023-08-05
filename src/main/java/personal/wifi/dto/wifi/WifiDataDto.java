package personal.wifi.dto.wifi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class WifiDataDto {

    @JsonProperty("X_SWIFI_MGR_NO")
    private String managementNo; // 관리번호

    @JsonProperty("X_SWIFI_WRDOFC")
    private String wardOffice; // 자치구

    @JsonProperty("X_SWIFI_MAIN_NM")
    private String mainName; // 와이파이명

    @JsonProperty("X_SWIFI_ADRES1")
    private String address1; // 도로명주소

    @JsonProperty("X_SWIFI_ADRES2")
    private String address2; // 상세주소

    @JsonProperty("X_SWIFI_INSTL_FLOOR")
    private String installationFloor; // 설치위치(층)

    @JsonProperty("X_SWIFI_INSTL_TY")
    private String installationType; // 설치유형

    @JsonProperty("X_SWIFI_INSTL_MBY")
    private String installationManufacturedBy; // 설치기관

    @JsonProperty("X_SWIFI_SVC_SE")
    private String serviceSeparatedEntry; // 서비스구분

    @JsonProperty("X_SWIFI_CMCWR")
    private String CMCWR; // 망종류

    @JsonProperty("X_SWIFI_CNSTC_YEAR")
    private String constructionYear; // 설치년도

    @JsonProperty("X_SWIFI_INOUT_DOOR")
    private String inoutDoor; // 실내외구분

    @JsonProperty("X_SWIFI_REMARS3")
    private String REMARS3; // wifi접속환경

    @JsonProperty("LAT")
    private Float latitude; // X좌표

    @JsonProperty("LNT")
    private Float longitude; // Y좌표

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.S")
    @JsonProperty("WORK_DTTM")
    private Timestamp workedDateTime; // 작업일자

}