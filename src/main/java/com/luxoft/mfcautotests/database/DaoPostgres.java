package com.luxoft.mfcautotests.database;

import com.luxoft.mfcautotests.config.annotations.InjectLogger;
import com.luxoft.mfcautotests.environment.TestEnvironment;
import com.luxoft.mfcautotests.model.InsuranceCompany;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource({"classpath:environment.properties"})
public class DaoPostgres implements DaoFactory {

    @InjectLogger
    Logger log;
    @Autowired
    TestEnvironment env;

    private Connection dbConnection;
    private PreparedStatement preparedStatement;

    private void getConnectionToDb(String url, String port, String dbName, String userName, String password) {
        try {
            Class.forName(env.dbPostgreSqlDriverRegistration);
            this.dbConnection = DriverManager.getConnection(env.dbPostgreSqlDriver + url + port + dbName, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error while getting connection to DB");
            e.printStackTrace();
        }
    }

    private void getDbWsConnection() {
        log.info("Getting connection to DB_WS");
        getConnectionToDb(env.dbWsUrl, env.dbWsPort, env.dbWsName, env.dbWsUserName, env.dbWsPassword);
    }

    private void getDbConnection() {
        log.info("Getting connection to DB");
        getConnectionToDb(env.dbUrl, env.dbPort, env.dbName, env.dbUserName, env.dbPassword);
    }

    public ResultSet executeQueryWithResultSet(String query) {
        try {
            preparedStatement = dbConnection.prepareStatement(query);
            log.info("Executing query");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    public int executeQueryWithUpdate(String query) {
        try {
            preparedStatement = dbConnection.prepareStatement(query);
            log.info("Executing query");
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
            return -100;
        }
    }

    private void closeStatementAndConnection() {
        try {
            if (preparedStatement != null) {
                this.preparedStatement.close();
            }
            if (dbConnection != null) {
                this.dbConnection.close();
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

        getDbWsConnection();

        log.info("Creating query 'insert mainMmcStats'");
        executeQueryWithUpdate(insertQuery);

        closeStatementAndConnection();
    }

    public void deleteMainStatsData(int daysToMinusFromCurrentDate) {
        String deleteQuery = "BEGIN; " +
                "DELETE FROM an_report_item_data WHERE andup_data_upload_id IN" +
                    "(SELECT andup_data_upload_id FROM an_data_upload WHERE andup_period_start = CURRENT_DATE - interval '" + daysToMinusFromCurrentDate + " day' + interval '8 hour');" +
                "DELETE FROM an_data_upload WHERE andup_period_start = CURRENT_DATE - interval '" + daysToMinusFromCurrentDate + " day' + interval '8 hour';" +
                "END;";

        getDbWsConnection();

        log.info("Creating query 'delete mainMmcStats'");
        executeQueryWithUpdate(deleteQuery);

        closeStatementAndConnection();
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
}
