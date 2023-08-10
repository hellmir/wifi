package personal.wifi.entity.wifi;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

// 테이블과의 매핑 가독성을 위해 @Table, @Id, @Column 어노테이션 사용

@Getter
@Entity
@Table(name = "wifi_data")
@NoArgsConstructor
public class WifiData {

    @Id
    @Column(name = "X_SWIFI_MGR_NO")
    private String managementNo; // 관리번호

    @Column(name = "X_SWIFI_WRDOFC")
    private String wardOffice; // 자치구

    @Column(name = "X_SWIFI_MAIN_NM")
    private String mainName; // 와이파이명

    @Column(name = "X_SWIFI_ADRES1")
    private String address1; // 도로명주소

    @Column(name = "X_SWIFI_ADRES2")
    private String address2; // 상세주소

    @Column(name = "X_SWIFI_INSTL_FLOOR")
    private String installationFloor; // 설치위치(층)

    @Column(name = "X_SWIFI_INSTL_TY")
    private String installationType; // 설치유형

    @Column(name = "X_SWIFI_INSTL_MBY")
    private String installationManufacturedBy; // 설치기관

    @Column(name = "X_SWIFI_SVC_SE")
    private String serviceSeparatedEntry; // 서비스구분

    @Column(name = "X_SWIFI_CMCWR")
    private String CMCWR; // 망종류

    @Column(name = "X_SWIFI_CNSTC_YEAR")
    private String constructionYear; // 설치년도

    @Column(name = "X_SWIFI_INOUT_DOOR")
    private String inoutDoor; // 실내외구분

    @Column(name = "X_SWIFI_REMARS3")
    private String REMARS3; // wifi접속환경

    @Column(name = "LAT")
    private Double latitude; // X좌표

    @Column(name = "LNT")
    private Double longitude; // Y좌표

    @Column(name = "WORK_DTTM")
    private Timestamp workedDateTime; // 작업일자

    private Double distance; // 현재 위치와의 거리(km)

    @Builder
    private WifiData(String managementNo, String wardOffice, String mainName,
                    String address1, String address2, String installationFloor, String installationType,
                    String installationManufacturedBy, String serviceSeparatedEntry,
                    String CMCWR, String constructionYear, String inoutDoor, String REMARS3,
                    Double latitude, Double longitude, Timestamp workedDateTime, Double distance) {

        this.managementNo = managementNo;
        this.wardOffice = wardOffice;
        this.mainName = mainName;
        this.address1 = address1;
        this.address2 = address2;
        this.installationFloor = installationFloor;
        this.installationType = installationType;
        this.installationManufacturedBy = installationManufacturedBy;
        this.serviceSeparatedEntry = serviceSeparatedEntry;
        this.CMCWR = CMCWR;
        this.constructionYear = constructionYear;
        this.inoutDoor = inoutDoor;
        this.REMARS3 = REMARS3;
        this.latitude = latitude;
        this.longitude = longitude;
        this.workedDateTime = workedDateTime;
        this.distance = distance;

    }

}