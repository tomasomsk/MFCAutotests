package com.luxoft.mfcautotests.database;

import com.luxoft.mfcautotests.FrameWork;
import com.luxoft.mfcautotests.environment.TestEnvironment;
import com.luxoft.mfcautotests.model.InsuranceCompany;
import com.luxoft.mfcautotests.model.MfcStatsItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@PropertySource({"classpath:environment.properties"})
public class DaoPostgres extends FrameWork {

    @Autowired
    TestEnvironment env;

    public Connection getConnectionToDb(String url, String port, String dbName, String userName, String password) {
        try {
            Class.forName(env.dbPostgreSqlDriverRegistration);
            return DriverManager.getConnection(env.dbPostgreSqlDriver + url + port + dbName, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error while getting connection to DB");
            e.printStackTrace();
            throw new RuntimeException("CAN'T CREATE CONNECTION");
        }
    }

    private Connection getDbWsConnection() {
        log.info("Getting connection to DB_WS");
        return getConnectionToDb(env.dbWsUrl, env.dbWsPort, env.dbWsName, env.dbWsUserName, env.dbWsPassword);
    }

    private Connection getDbConnection() {
        log.info("Getting connection to DB");
        return getConnectionToDb(env.dbUrl, env.dbPort, env.dbName, env.dbUserName, env.dbPassword);
    }

    public void insertMainStatsData(int daysToMinusFromCurrentDate) {
        log.info("Creating query 'insert mainMmcStats'");
        String insertQuery = "BEGIN;" +
                "INSERT INTO an_data_upload (andut_data_upload_type_id, andup_committed, andup_period_start, andup_period_end) VALUES (1, CURRENT_TIMESTAMP, CURRENT_DATE - interval '" + daysToMinusFromCurrentDate + " day' + interval '8 hour', CURRENT_DATE - interval '" + (daysToMinusFromCurrentDate - 1) + " day' +  interval '8 hour');" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 7,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 8,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 1745,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5033,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 467,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 468,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 525,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 528,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 1503,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 1505,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 1507,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5004,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5034,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 1509,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 1511,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 310,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 1767,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 1387,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 1388,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 344,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5049,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 1769,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 44,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 45,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5029,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5028,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5053,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5054,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5056,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5030,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5055,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5032,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5057,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5025,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5050,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5051,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5026,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5052,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5027,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5031,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5038,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5040,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5357,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5361,trunc(random() * 1000));" +
                "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES ((SELECT MAX (andup_data_upload_id) FROM an_data_upload), 5363,trunc(random() * 1000));" +
                "END;";

        try (
                Connection connection = getDbWsConnection();
                PreparedStatement st = connection.prepareStatement(insertQuery)
        ) {
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("CAN'T INSERT mainMmcStats");
        }
    }

    public void deleteMainStatsData(int daysToMinusFromCurrentDate) {
        log.info("Deleting mainStatsData");
        String deleteQuery = "BEGIN; " +
                "DELETE FROM an_report_item_data WHERE andup_data_upload_id IN" +
                "(SELECT andup_data_upload_id FROM an_data_upload WHERE andup_period_start = CURRENT_DATE - interval '" + daysToMinusFromCurrentDate + " day' + interval '8 hour');" +
                "DELETE FROM an_data_upload WHERE andup_period_start = CURRENT_DATE - interval '" + daysToMinusFromCurrentDate + " day' + interval '8 hour';" +
                "END;";
        try (
                Connection connection = getDbWsConnection();
                PreparedStatement st = connection.prepareStatement(deleteQuery)
        ) {
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("CAN'T DELETE mainMmcStats");
        }
    }

    public List<InsuranceCompany> selectDmsCompaniesSortByAdminUiSeq() {
        log.info("Creating query 'SELECT Insurance Companies sorting by admin_ui sequence ASC'");
        String selectQuery = "SELECT eddis_name, paidi_dms_admin_ui_seq_no, paidi_ui_seq_no FROM pa_integrated_dms_issuer JOIN ed_dms_issuer " +
                "ON pa_integrated_dms_issuer.eddis_dms_issuer_id=ed_dms_issuer.eddis_dms_issuer_id " +
                "ORDER BY paidi_dms_admin_ui_seq_no ASC";
        List<InsuranceCompany> insuranceCompanies = new ArrayList<>();
        try (
                Connection connection = getDbConnection();
                PreparedStatement st = connection.prepareStatement(selectQuery)
        ) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                InsuranceCompany company = new InsuranceCompany();
                company.setName(resultSet.getString("eddis_name"));
                company.setAdminUiSeqNo(resultSet.getInt("paidi_dms_admin_ui_seq_no"));
                company.setUiSeqNo(resultSet.getInt("paidi_ui_seq_no"));
                insuranceCompanies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("CAN'T SELECT Insurance Companies");

        }

        return insuranceCompanies;
    }

    public void insertInAnDataUpload(int type, LocalDateTime periodStart) {
        log.info("Creating query to insert in an_data_upload");
        String insertQuerry = "INSERT INTO an_data_upload (andut_data_upload_type_id, andup_committed, andup_period_start, " +
                "andup_period_end) VALUES (?, ?, ?, ?)";
        try (
                Connection connection = getDbConnection();
                PreparedStatement st = connection.prepareStatement(insertQuerry)
        ) {
            st.setInt(1, type);
            st.setObject(2, LocalDateTime.now());
            st.setObject(3, periodStart);
            st.setObject(4, periodStart.plusDays(1));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("CAN'T INSERT INTO an_data_upload");
        }


    }

    public void deleteFromAnDataUpload(LocalDateTime periodStart) {
        log.info("Creating query 'delete from an_data_upload'");
        String deleteQuery = "DELETE FROM an_data_upload WHERE andup_period_start = ?";
        try (
                Connection connection = getDbConnection();
                PreparedStatement st = connection.prepareStatement(deleteQuery)
        ) {
            st.setObject(1, periodStart);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("CAN'T DELETE FROM an_data_upload");
        }
    }

    public int selectMaxValueFromColumnInTable(String tableName, String columnName) {
        log.info("Creating querry to SELECT MAX value from column " + columnName + " in table " + tableName);
        String selectQuery = "SELECT MAX (" + columnName + ") FROM "+ tableName;
        try (
                Connection connection = getDbConnection();
                PreparedStatement st = connection.prepareStatement(selectQuery)
        ) {
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("CAN'T SELECT MAX FROM TABLE");
        }
    }

    public List<Integer> selectDataUploadIdsForDate(LocalDateTime periodStart) {
        log.info("Selecting data_upload_id depends on date " + periodStart);
        String selectQuery = "SELECT andup_data_upload_id FROM an_data_upload WHERE andup_period_start = ?";
        List<Integer> dataUploadIds = new ArrayList<>();
        try (
                Connection connection = getDbConnection();
                PreparedStatement st = connection.prepareStatement(selectQuery)
        ) {
            st.setObject(1, periodStart);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                int dataUploadId = resultSet.getInt(1);
                dataUploadIds.add(dataUploadId);
            }
            return dataUploadIds;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("CAN'T SELECT DATA_UPLOAD_IDS");
        }
    }

    public void deleteFromAnReportItemData(List<Integer> dataUploadIds) {
        log.info("Creating query 'delete from an_report_item_data'. data_upload_ids IN " + dataUploadIds.toString());
        String deleteQuery = "DELETE FROM an_report_item_data WHERE andup_data_upload_id = ?";
        try (
                Connection connection = getDbConnection();
                PreparedStatement st = connection.prepareStatement(deleteQuery)
        ) {
//            st.setObject(1, getSubstringFromString(dataUploadIds.toString(), "\\[(.*)\\]"));
            final int batchSize = 1000;
            int count = 0;
            for (int dataUploadId : dataUploadIds) {
                st.setInt(1, dataUploadId);
                st.addBatch();
                if (++count % batchSize == 0) {
                    st.executeBatch();
                }
            }
            st.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("CAN'T DELETE FROM an_report_item_data");
        }
    }

    public void insertInReportItemDataItemsWithSource(List<MfcStatsItem> mfcStatsItems, LocalDateTime periodStart) {
        log.info("Creating query to insert into an_data_upload");
        String insertQuery = "INSERT INTO an_data_upload (andut_data_upload_type_id, andup_committed, andup_period_start, andup_period_end) VALUES (?, ?, ?, ?)";
        try (
                Connection connection = getDbConnection();
                PreparedStatement psForAnDataUpload = connection.prepareStatement(insertQuery)
        ) {
            psForAnDataUpload.setInt(1, mfcStatsItems.get(0).getFillingType());
            psForAnDataUpload.setObject(2, LocalDateTime.now());
            psForAnDataUpload.setObject(3, periodStart);
            psForAnDataUpload.setObject(4, periodStart.plusDays(1));
            psForAnDataUpload.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("CAN'T INSERT INTO an_data_upload");
        }

        int dataUpload = selectMaxValueFromColumnInTable("an_data_upload", "andup_data_upload_id");

        log.info("Creating query to insert into an_report_item_data");
        insertQuery = "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES (?, ?, ?)";
        try (Connection connection = getDbConnection();
             PreparedStatement psForReportItemData = connection.prepareStatement(insertQuery)
        ) {
            final int batchSize = 1000;
            int count = 0;
            for (MfcStatsItem item : mfcStatsItems) {
                log.info("Inserting item " + item.getParameter());
                psForReportItemData.setInt(1, dataUpload);
                if (!"x".equalsIgnoreCase(item.getItemIdPeriod())) {
                    psForReportItemData.setDouble(2, Double.parseDouble(item.getItemIdPeriod()));
                    psForReportItemData.setDouble(3, Double.parseDouble(item.getValue()));
                    psForReportItemData.addBatch();
                }
                if (!"x".equalsIgnoreCase(item.getItemIdYear())) {
                    psForReportItemData.setDouble(2, Double.parseDouble(item.getItemIdYear()));
                    psForReportItemData.setDouble(3, Double.parseDouble(item.getValueYear()));
                    psForReportItemData.addBatch();

                }
                if (!"x".equalsIgnoreCase(item.getItemIdPrevYear())) {
                    psForReportItemData.setDouble(2, Double.parseDouble(item.getItemIdPrevYear()));
                    psForReportItemData.setDouble(3, Double.parseDouble(item.getValuePrevYear()));
                    psForReportItemData.addBatch();
                }
                if (++count % batchSize == 0) {
                    psForReportItemData.executeBatch();
                }
            }
            psForReportItemData.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("CAN'T INSERT INTO an_report_item_data");
        }
    }
}

