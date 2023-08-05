package personal.wifi.repository.wifi;

import org.springframework.stereotype.Repository;
import personal.wifi.entity.wifi.WifiData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WifiRepository {

    public WifiRepository() {

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
             Statement stmt = c.createStatement()) {


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
             Statement stmt = c.createStatement()) {

            stmt.execute("DROP TABLE IF EXISTS WIFI_TABLE");

            String sql = "CREATE TABLE WIFI_TABLE " +
                    "(X_SWIFI_MGR_NO TEXT PRIMARY KEY NOT NULL, " +
                    "X_SWIFI_WRDOFC TEXT NOT NULL, " +
                    "X_SWIFI_MAIN_NM TEXT NOT NULL, " +
                    "X_SWIFI_ADRES1 TEXT NOT NULL, " +
                    "X_SWIFI_ADRES2 TEXT NOT NULL, " +
                    "X_SWIFI_INSTL_FLOOR TEXT NOT NULL, " +
                    "X_SWIFI_INSTL_TY TEXT NOT NULL, " +
                    "X_SWIFI_INSTL_MBY TEXT NOT NULL, " +
                    "X_SWIFI_SVC_SE TEXT NOT NULL, " +
                    "X_SWIFI_CMCWR TEXT NOT NULL, " +
                    "X_SWIFI_CNSTC_YEAR TEXT NOT NULL, " +
                    "X_SWIFI_INOUT_DOOR TEXT NOT NULL, " +
                    "X_SWIFI_REMARS3 TEXT NOT NULL, " +
                    "LAT TEXT NOT NULL, " +
                    "LNT TEXT NOT NULL, " +
                    "WORK_DTTM TEXT NOT NULL)";
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public void save(WifiData wifiInformation) {

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
             Statement stmt = c.createStatement()) {

            String sql = "INSERT INTO WIFI_TABLE (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1," +
                    "X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE," +
                    "X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                    "VALUES ('" + wifiInformation.getManagementNo() + "', '" + wifiInformation.getWardOffice()
                    + "', '" + wifiInformation.getMainName() + "', '" + wifiInformation.getAddress1()
                    + "', '" + wifiInformation.getAddress2() + "', '" + wifiInformation.getInstallationFloor()
                    + "', '" + wifiInformation.getInstallationType() + "', '" + wifiInformation.getInstallationManufacturedBy()
                    + "', '" + wifiInformation.getServiceSeparatedEntry() + "', '" + wifiInformation.getCMCWR()
                    + "', '" + wifiInformation.getConstructionYear() + "', '" + wifiInformation.getInoutDoor()
                    + "', '" + wifiInformation.getREMARS3() + "', '" + wifiInformation.getLatitude()
                    + "', '" + wifiInformation.getLongitude() + "', '" + wifiInformation.getWorkedDateTime() + "')";
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

}