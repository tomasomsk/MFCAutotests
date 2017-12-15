package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.config.annotations.Helper;
import com.luxoft.mfcautotests.database.DaoPostgres;
import com.luxoft.mfcautotests.model.MfcStatsGroup;
import com.luxoft.mfcautotests.model.MfcStatsItem;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

@Helper
public class StatsHelper extends ServicesHelper {

    @Autowired
    SSHHelper sshHelper;
//    @Autowired
//    DailyReportPage dailyReportPage;
    @Autowired
    DaoPostgres daoPostgres;

    public boolean dateFromServerLessThenEightAm() {
        Date dateFromServer = sshHelper.getDateFromServer(env.dbUrl);
        Date currentDateEightOClock = getCurrentDateWithDefinedTime(8, 0, 0);
        return dateFromServer.compareTo(currentDateEightOClock) < 1;
    }

    public List<MfcStatsGroup> getMfcDailyStatsFromExcel() {
        List<MfcStatsGroup> mfcDailyStats = new ArrayList<>();
        try {
            File statsFile = new File(env.resourcesPath + "/excel/statistic/dailyStatsItems.xlsx");
            XSSFWorkbook mfcStats = new XSSFWorkbook(statsFile);
            XSSFSheet mfcStatsSheet = mfcStats.getSheetAt(0);
            Iterator<Row> rows = mfcStatsSheet.rowIterator();
            int i = 0;
            rows.next();
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();

                XSSFCell groupNameCell = row.getCell(0);
                XSSFCell numberCell = row.getCell(1);
                XSSFCell mfcStatsItemNameCell = row.getCell(2);
                XSSFCell calculationLogicAccountingPeriodCell = row.getCell(3);
                XSSFCell calculationLogicCurrentYearCell = row.getCell(4);
                XSSFCell calculationLogicLastYearCell = row.getCell(5);
                XSSFCell dimensionCell = row.getCell(6);
                XSSFCell fillingTypeCell = row.getCell(7);
                XSSFCell dataSourceCell = row.getCell(8);
                XSSFCell itemCodeCell = row.getCell(9);
                XSSFCell isDashboardCell = row.getCell(10);
                XSSFCell itemIdPeriodCell = row.getCell(11);
                XSSFCell itemIdYearCell = row.getCell(12);
                XSSFCell itemIdPrevYearCell = row.getCell(13);
                XSSFCell parameterCell = row.getCell(14);
                XSSFCell valueCell = row.getCell(15);


                if (groupNameCell != null && !"".equals(groupNameCell.getStringCellValue())) {
                    i += 1;
                    MfcStatsGroup mfcStatsGroup = new MfcStatsGroup();
                    String sectionName = groupNameCell.getStringCellValue();
                    mfcStatsGroup.setName(sectionName);
                    mfcDailyStats.add(mfcStatsGroup);
                }

                if (mfcStatsItemNameCell != null) {
                    MfcStatsItem mfcStatsItem = new MfcStatsItem();

                    mfcStatsItem.setName(mfcStatsItemNameCell.getStringCellValue());
                    mfcStatsItem.setNumber((int) Double.parseDouble(getValueFromCell(numberCell)));
                    mfcStatsItem.setCalculationLogicAccountingPeriod(getValueFromCell(calculationLogicAccountingPeriodCell));
                    mfcStatsItem.setCalculationLogicCurrentYear(getValueFromCell(calculationLogicCurrentYearCell));
                    mfcStatsItem.setCalculationLogicLastYear(getValueFromCell(calculationLogicLastYearCell));
                    mfcStatsItem.setDimension(getValueFromCell(dimensionCell));
                    mfcStatsItem.setFillingType("Авт.".equalsIgnoreCase(getValueFromCell(fillingTypeCell))
                            ? 1 : 2);
                    mfcStatsItem.setDataSource(switchDataSourceValue(getValueFromCell(dataSourceCell)));
                    mfcStatsItem.setItemCode((int) Double.parseDouble(getValueFromCell(itemCodeCell)));
                    mfcStatsItem.setIsDashboard("Да".equalsIgnoreCase(getValueFromCell(isDashboardCell))
                            ? "Y" : "");
                    mfcStatsItem.setItemIdPeriod(getValueFromCell(itemIdPeriodCell));
                    mfcStatsItem.setItemIdYear(getValueFromCell(itemIdYearCell));
                    mfcStatsItem.setItemIdPrevYear(getValueFromCell(itemIdPrevYearCell));
                    mfcStatsItem.setParameter(getValueFromCell(parameterCell));
                    mfcStatsItem.setValue(getValueFromCell(valueCell));

                    mfcDailyStats.get(i - 1).addMfcStatsItem(mfcStatsItem);
                }
            }

            return mfcDailyStats;

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't get daily stats from excel file");
        }
    }

    public int switchDataSourceValue(String valueFromCell) {
        switch (valueFromCell.toUpperCase()) {
            case "ЕМИАС" : return 1;
            case "БАНК" : return 2;
            case "ЭЛ. ОЧЕРЕДЬ" : return 3;
            case "ИС ММЦ" : return 5;
            case "ММЦ (ВРУЧНУЮ)" : return 6;
            case "УФМС" : return 8;
            case "РАСЧЕТ" : return 9;
            case "ИС ТЕСТИР." : return 16;
            default : return Integer.MAX_VALUE;
        }
    }

    public static String getValueFromCell(XSSFCell cell) {

        return Objects.nonNull(cell) ? cell.getCellTypeEnum().equals(CellType.NUMERIC)
                ? String.valueOf(cell.getNumericCellValue()) : cell.getStringCellValue() : "";
    }

    public void insertDailyStatsItemsToDb(LocalDateTime periodStart) {
        List<MfcStatsGroup> mfcDailyStats = getMfcDailyStatsFromExcel();

        List<MfcStatsItem> itemsWithSourceEmias = getItemsWithSource(1, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceBank = getItemsWithSource(2, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceQms = getItemsWithSource(3, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceMfcAuto = getItemsWithSource(5, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceMfcManual = getItemsWithSource(6, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourcePpot = getItemsWithSource(8, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceTesting = getItemsWithSource(16, mfcDailyStats);

        List<List<MfcStatsItem>> dataSourceSeparatedItems = new ArrayList<>();
        dataSourceSeparatedItems.add(itemsWithSourceEmias);
        dataSourceSeparatedItems.add(itemsWithSourceBank);
        dataSourceSeparatedItems.add(itemsWithSourceQms);
        dataSourceSeparatedItems.add(itemsWithSourceMfcAuto);
        dataSourceSeparatedItems.add(itemsWithSourceMfcManual);
        dataSourceSeparatedItems.add(itemsWithSourcePpot);
        dataSourceSeparatedItems.add(itemsWithSourceTesting);

        List<Integer> dataUploadIds = daoPostgres.selectDataUploadIdsForDate(periodStart);
        daoPostgres.deleteFromAnReportItemData(dataUploadIds);
        daoPostgres.deleteFromAnDataUpload(periodStart);



        int dataUploadId;
        for (int i = 0; i < dataSourceSeparatedItems.size(); i++) {
            int fillingType = dataSourceSeparatedItems.get(i).get(0).getFillingType();
            List<MfcStatsItem> items = dataSourceSeparatedItems.get(i);
            daoPostgres.insertInAnDataUpload(fillingType, periodStart);
            dataUploadId = daoPostgres.selectMaxValueFromColumnInTable("an_data_upload", "andup_data_upload_id");
            for (int k = 0; k < items.size(); k++) {
                if (!"x".equalsIgnoreCase(items.get(k).getItemIdPeriod())) {
                    daoPostgres.insertInAnReportItemData(dataUploadId, items.get(k).getItemIdPeriod(), items.get(k).getValue());
                }
                if (!"x".equalsIgnoreCase(items.get(k).getItemIdYear())) {
                    daoPostgres.insertInAnReportItemData(dataUploadId, items.get(k).getItemIdYear(), items.get(k).getValue());
                }
                if (!"x".equalsIgnoreCase(items.get(k).getItemIdPrevYear())) {
                    daoPostgres.insertInAnReportItemData(dataUploadId, items.get(k).getItemIdPrevYear(), items.get(k).getValue());
                }
            }
        }
    }

    public List<MfcStatsItem> getItemsWithSource(int sourceType, List<MfcStatsGroup> mfcDailyStats) {
        return mfcDailyStats.stream()
                .map(MfcStatsGroup::getMfcStatsItems)
                .flatMap(Collection::stream)
                .filter(item -> item.getDataSource() == sourceType)
                .collect(Collectors.toList());
    }
}