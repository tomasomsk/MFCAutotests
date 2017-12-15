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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource({"classpath:environment.properties"})
public class DaoPostgres extends FrameWork{

    @Autowired
    TestEnvironment env;

//    private Connection dbConnection;
//    private PreparedStatement preparedStatement;

    public Connection getConnectionToDb(String url, String port, String dbName, String userName, String password) {
        try {
            Class.forName(env.dbPostgreSqlDriverRegistration);
            return DriverManager.getConnection(env.dbPostgreSqlDriver + url + port + dbName, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error while getting connection to DB");
            e.printStackTrace();
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

//    public ResultSet executeQueryWithResultSet(String query) {
//        try {
//            preparedStatement = dbConnection.prepareStatement(query);
//            log.info("Executing query");
//            return preparedStatement.executeQuery();
//        } catch (SQLException e) {
//            e.getMessage();
//            e.printStackTrace();
//            throw new RuntimeException("Can't execute query with resultSet");
//        }
//    }
//
//    public int executeQueryWithUpdate(String query) {
//        try {
//            preparedStatement = dbConnection.prepareStatement(query);
//            log.info("Executing query");
//            return preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.getMessage();
//            e.printStackTrace();
//            return -100;
//        }
//    }
//
    private void closeConnection(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMainStatsData(int daysToMinusFromCurrentDate) {
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

        log.info("Creating query 'insert mainMmcStats'");
        try {
            PreparedStatement st = getDbWsConnection().prepareStatement(insertQuery);
            st.execute();
            closeConnection(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMainStatsData(int daysToMinusFromCurrentDate) {
        String deleteQuery = "BEGIN; " +
                "DELETE FROM an_report_item_data WHERE andup_data_upload_id IN" +
                "(SELECT andup_data_upload_id FROM an_data_upload WHERE andup_period_start = CURRENT_DATE - interval '" + daysToMinusFromCurrentDate + " day' + interval '8 hour');" +
                "DELETE FROM an_data_upload WHERE andup_period_start = CURRENT_DATE - interval '" + daysToMinusFromCurrentDate + " day' + interval '8 hour';" +
                "END;";
        try {
            Connection connection = getDbWsConnection();
            PreparedStatement st = connection.prepareStatement(deleteQuery);
            st.execute();
            closeConnection(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<InsuranceCompany> selectDmsCompaniesSortByAdminUiSeq() {
        String selectQuery = "SELECT eddis_name, paidi_dms_admin_ui_seq_no, paidi_ui_seq_no FROM pa_integrated_dms_issuer JOIN ed_dms_issuer " +
                "ON pa_integrated_dms_issuer.eddis_dms_issuer_id=ed_dms_issuer.eddis_dms_issuer_id " +
                "ORDER BY paidi_dms_admin_ui_seq_no ASC";

        getDbConnection();

        log.info("Creating query 'SELECT Insurance Companies sorting by admin_ui sequence ASC'");
        ResultSet resultSet = executeQueryWithResultSet(selectQuery);

        List<InsuranceCompany> insuranceCompanies = new ArrayList<>();
        try {
            while (resultSet.next()) {
                InsuranceCompany company = new InsuranceCompany();
                company.setName(resultSet.getString("eddis_name"));
                company.setAdminUiSeqNo(resultSet.getInt("paidi_dms_admin_ui_seq_no"));
                company.setUiSeqNo(resultSet.getInt("paidi_ui_seq_no"));
                insuranceCompanies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeStatementAndConnection();

        return insuranceCompanies;
    }

    public void insertInAnDataUpload(int type, LocalDateTime periodStart) {
        String insertQuerry = "INSERT INTO an_data_upload (andut_data_upload_type_id, andup_committed, andup_period_start, " +
                "andup_period_end) VALUES (" + type + ", CURRENT_TIMESTAMP, '" + formatDate(periodStart) + "', '" + periodStart.plusDays(1) + "')";

        getDbConnection();

        log.info("Creating query to insert in an_data_upload");
        executeQueryWithUpdate(insertQuerry);

        closeStatementAndConnection();
    }

    public void deleteFromAnDataUpload(LocalDateTime periodStart) {
        String deleteQuery = "DELETE FROM an_data_upload WHERE andup_period_start = '" + formatDate(periodStart) + "'";
        System.out.println(deleteQuery);

        getDbWsConnection();

        log.info("Creating query 'delete from an_data_upload'");
        executeQueryWithUpdate(deleteQuery);

        closeStatementAndConnection();
    }

    public int selectMaxValueFromColumnInTable(String tableName, String columnName) {
        String selectQuery = "SELECT MAX (" + columnName + ") FROM " + tableName + "";

        getDbWsConnection();

        log.info("Creating querry to SELECT MAX value from column " + columnName + " in table " + tableName);
        ResultSet resultSet = executeQueryWithResultSet(selectQuery);
        try {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("CAN'T SELECT MAX FROM TABLE");
        } finally {
            closeStatementAndConnection();
        }
    }

    public List<Integer> selectDataUploadIdsForDate(LocalDateTime periodStart) {
        String selectQuery = "SELECT andup_data_upload_id FROM an_data_upload WHERE andup_period_start ='" + formatDate(periodStart) + "'";
        System.out.println(selectQuery);

        getDbWsConnection();

        ResultSet resultSet = executeQueryWithResultSet(selectQuery);
        List<Integer> dataUploadIds = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int dataUploadId = resultSet.getInt(1);
                dataUploadIds.add(dataUploadId);
            }
            return dataUploadIds;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("CAN'T SELECT DATA_UPLOAD_IDS");
        } finally {
            closeStatementAndConnection();
        }
    }

    public void insertInAnReportItemData(int dataUploadId, String itemId, String value) {
        String insertQuery = "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) " +
                "VALUES (" + dataUploadId + ", " + itemId + ", " + value + ")";

        getDbWsConnection();

        log.info("Creating querry to insert in an_report_item_data");
        executeQueryWithUpdate(insertQuery);

        closeStatementAndConnection();
    }

    public void deleteFromAnReportItemData(List<Integer> dataUploadIds) {
        String deleteQuery = "DELETE FROM an_report_item_data WHERE andup_data_upload_id IN " +
                "(" + getSubstringFromString(dataUploadIds.toString(), "\\[(.*)\\]") + ")";
        System.out.println(deleteQuery);

        getDbWsConnection();

        log.info("Creating query 'delete from an_report_item_data'");
        executeQueryWithUpdate(deleteQuery);

        closeStatementAndConnection();
    }

    public void insertInReportItemDataItemsWithSource(List<MfcStatsItem> mfcStatsItems, LocalDateTime periodStart) {
        try {
            String sql = "INSERT INTO an_data_upload (andut_data_upload_type_id, andup_committed, andup_period_start, andup_period_end) VALUES (?, ?, ?, ?)";
            log.info("Creating query to insert into an_data_upload");
            getDbWsConnection();
            PreparedStatement psForAnDataUpload = dbConnection.prepareStatement(sql);
            psForAnDataUpload.setInt(1, mfcStatsItems.get(0).getFillingType());
            psForAnDataUpload.setObject(2, LocalDateTime.now());
            psForAnDataUpload.setObject(3, periodStart);
            psForAnDataUpload.setObject(4, periodStart.plusDays(1));
            psForAnDataUpload.executeUpdate();

            int dataUpload = selectMaxValueFromColumnInTable("an_data_upload", "andup_data_upload_id");

            log.info("Creating query to insert into an_report_item_data");
            sql = "INSERT INTO an_report_item_data (andup_data_upload_id, anrei_report_item_id, anred_value) VALUES (?, ?, ?)";
            getDbWsConnection();
            PreparedStatement psForReportItemData = dbConnection.prepareStatement(sql);
            final int batchSize = 1000;
            int count = 0;
            for (MfcStatsItem item : mfcStatsItems) {
                psForReportItemData.setInt(1, dataUpload);
                if (!"x".equalsIgnoreCase(item.getItemIdPeriod())) {
                    psForReportItemData.setDouble(2, Double.parseDouble(item.getItemIdPeriod()));
                }
                if (!"x".equalsIgnoreCase(item.getItemIdYear())) {
                    psForReportItemData.setDouble(2, Double.parseDouble(item.getItemIdYear()));
                }
                if (!"x".equalsIgnoreCase(item.getItemIdPrevYear())) {
                    psForReportItemData.setDouble(2, Double.parseDouble(item.getItemIdPrevYear()));
                }
                psForReportItemData.setDouble(3, Double.parseDouble(item.getValue()));
                psForReportItemData.addBatch();

                if (++count % batchSize == 0) {
                    psForReportItemData.executeBatch();
                }
            }
            psForReportItemData.executeBatch();
            psForReportItemData.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeStatementAndConnection();
        }
    }
}

