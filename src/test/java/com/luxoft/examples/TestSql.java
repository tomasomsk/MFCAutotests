package com.luxoft.examples;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.config.annotations.NonDriver;
import com.luxoft.mfcautotests.database.DaoPostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class TestSql extends BaseTest {

    @Autowired
    DaoPostgres daoPostgres;

    @Test
    @NonDriver
    public void testSql() {
        LocalDateTime periodStart = LocalDateTime.of(2017, 12, 14, 8, 0, 0);

        System.out.println(daoPostgres.selectMaxValueFromColumnInTable("an_data_upload", "andup_data_upload_id"));
    }
}
