package personal.wifi.repository.wifi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import personal.wifi.entity.wifi.WifiData;

import java.util.List;

public interface WifiRepository extends JpaRepository<WifiData, Long> {

    @Query(value = "SELECT w.X_SWIFI_MGR_NO," +
            "w.X_SWIFI_WRDOFC," +
            "w.X_SWIFI_MAIN_NM," +
            "w.X_SWIFI_ADRES1," +
            "w.X_SWIFI_ADRES2," +
            "w.X_SWIFI_INSTL_FLOOR," +
            "w.X_SWIFI_INSTL_TY," +
            "w.X_SWIFI_INSTL_MBY," +
            "w.X_SWIFI_SVC_SE," +
            "w.X_SWIFI_CMCWR," +
            "w.X_SWIFI_CNSTC_YEAR," +
            "w.X_SWIFI_INOUT_DOOR," +
            "w.X_SWIFI_REMARS3," +
            "w.LAT," +
            "w.LNT," +
            "w.WORK_DTTM, " +
            "(6371 * acos(cos(radians(:latitude)) " +
            "* cos(radians(w.LNT)) " +
            "* cos(radians(w.LAT) - radians(:longitude)) " +
            "+ sin(radians(:latitude)) " +
            "* sin(radians(w.LNT)))) AS distance " +
            "FROM WIFI_DATA w " +
            "ORDER BY distance " +
            "LIMIT 20", nativeQuery = true)
    List<WifiData> findNearTwentyWifis(@Param("latitude") double latitude, @Param("longitude") double longitude);


}
