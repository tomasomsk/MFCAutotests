package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.config.annotations.Helper;
import com.luxoft.mfcautotests.database.DaoPostgres;
import com.luxoft.mfcautotests.model.MfcStatsGroup;
import com.luxoft.mfcautotests.model.MfcStatsItem;
import com.luxoft.mfcautotests.model.MfcStatsItemForEquals;
import com.luxoft.mfcautotests.model.StatsSource;
import com.luxoft.mfcautotests.pages.stats.DailyReportPage;
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
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

@Helper
public class StatsHelper extends ServicesHelper {

    @Autowired
    SSHHelper sshHelper;
    @Autowired
    DailyReportPage dailyReportPage;
    @Autowired
    DaoPostgres daoPostgres;

    public List<MfcStatsGroup> mfcDailyStats;

    public boolean dateFromServerLessThenEightAm() {
        Date dateFromServer = sshHelper.getDateFromServer(env.dbUrl);
        Date currentDateEightOClock = getCurrentDateWithDefinedTime(8, 0, 0);
        return dateFromServer.compareTo(currentDateEightOClock) < 1;
    }

    public boolean currentTimeLessThanEightAm() {
        return LocalTime.now().compareTo(LocalTime.of(8, 0, 0, 0)) < 0;
    }

    public List<MfcStatsGroup> getMfcDailyStatsFromExcel() {
        log.info("Getting statistic from Excel");

        if (mfcDailyStats != null) {
            return mfcDailyStats;
        }
        try {
            mfcDailyStats = new ArrayList<>();
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
                XSSFCell valueYearCell = row.getCell(16);
                XSSFCell valuePrevYearCell = row.getCell(17);


                if (groupNameCell != null && !"".equals(groupNameCell.getStringCellValue())) {
                    i += 1;
                    MfcStatsGroup mfcStatsGroup = new MfcStatsGroup();
                    String groupName = groupNameCell.getStringCellValue();
                    mfcStatsGroup.setName(groupName);
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
                    mfcStatsItem.setValue(getValueFromCell(valueCell));
                    mfcStatsItem.setItemIdYear(getValueFromCell(itemIdYearCell));
                    mfcStatsItem.setValueYear(getValueFromCell(valueYearCell));
                    mfcStatsItem.setItemIdPrevYear(getValueFromCell(itemIdPrevYearCell));
                    mfcStatsItem.setValuePrevYear(getValueFromCell(valuePrevYearCell));
                    mfcStatsItem.setParameter(getValueFromCell(parameterCell));

                    mfcDailyStats.get(i - 1).addMfcStatsItem(mfcStatsItem);
                }
            }

            return mfcDailyStats;

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't get daily stats from excel file");
        }
    }

    public int switchDataSourceValue(String value) {
        switch (value.toUpperCase()) {
            case "ЕМИАС":
                return 1;
            case "БАНК":
                return 2;
            case "ЭЛ. ОЧЕРЕДЬ":
                return 3;
            case "ИС ММЦ":
                return 5;
            case "ММЦ (ВРУЧНУЮ)":
                return 6;
            case "РАСЧЕТ":
                return 9;
            case "УФМС":
                return 14;
            case "ИС ТЕСТИР.":
                return 16;
            default:
                return Integer.MAX_VALUE;
        }
    }

    public String getValueFromCell(XSSFCell cell) {

        return Objects.nonNull(cell) ? cell.getCellTypeEnum().equals(CellType.NUMERIC)
                ? String.valueOf(cell.getNumericCellValue()) : cell.getStringCellValue() : "";
    }

    public void insertDailyStatsItemsToDb(LocalDateTime periodStart) {
        mfcDailyStats = getMfcDailyStatsFromExcel();

        List<MfcStatsItem> itemsWithSourceEmias = getItemsWithSource(StatsSource.MED, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceBank = getItemsWithSource(StatsSource.BANK, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceQms = getItemsWithSource(StatsSource.QMS, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceMfcAuto = getItemsWithSource(StatsSource.IS_MMC, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceMfcManual = getItemsWithSource(StatsSource.MFC, mfcDailyStats);
//        List<MfcStatsItem> itemsWithSourcePpot = getItemsWithSource(StatsSource.PPOT, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceUfms = getItemsWithSource(StatsSource.UFMS, mfcDailyStats);
        List<MfcStatsItem> itemsWithSourceTesting = getItemsWithSource(StatsSource.TESTING, mfcDailyStats);

        List<List<MfcStatsItem>> dataSourceSeparatedItems = new ArrayList<>();
        dataSourceSeparatedItems.add(itemsWithSourceEmias);
        dataSourceSeparatedItems.add(itemsWithSourceBank);
        dataSourceSeparatedItems.add(itemsWithSourceQms);
        dataSourceSeparatedItems.add(itemsWithSourceMfcAuto);
        dataSourceSeparatedItems.add(itemsWithSourceMfcManual);
//        dataSourceSeparatedItems.add(itemsWithSourcePpot);
        dataSourceSeparatedItems.add(itemsWithSourceUfms);
        dataSourceSeparatedItems.add(itemsWithSourceTesting);

        for (List<MfcStatsItem> itemsList : dataSourceSeparatedItems) {
            daoPostgres.insertInReportItemDataItemsWithSource(itemsList, periodStart);
        }
    }

    public StatsHelper deleteDailyStatsFromDb(LocalDateTime periodStart) {
        List<Integer> dataUploadIds = daoPostgres.selectDataUploadIdsForDate(periodStart);
        if (!dataUploadIds.isEmpty()) {
            daoPostgres.deleteFromAnReportItemData(dataUploadIds);
        }
        daoPostgres.deleteFromAnDataUpload(periodStart);
        return this;
    }

//    public List<MfcStatsItem> getItemsWithSource(int sourceType, List<MfcStatsGroup> mfcDailyStats) {
//        return mfcDailyStats.stream()
//                .map(MfcStatsGroup::getMfcStatsItems)
//                .flatMap(Collection::stream)
//                .filter(item -> item.getDataSource() == sourceType)
//                .collect(Collectors.toList());
//    }

    public List<MfcStatsItem> getItemsWithSource(StatsSource source, List<MfcStatsGroup> mfcDailyStats) {
        int sourceType;
        switch (source) {
            case MED:
                sourceType = 1;
                break;
            case BANK:
                sourceType = 2;
                break;
            case QMS:
                sourceType = 3;
                break;
            case TESTING:
                sourceType = 16;
                break;
            case IS_MMC:
                sourceType = 5;
                break;
            case MFC:
                sourceType = 6;
                break;
            case PAPILON:
                sourceType = 7;
                break;
            case PPOT:
                sourceType = 8;
                break;
            case CALC:
                sourceType = 9;
                break;
            case DEP_ZDRAV:
                sourceType = 10;
                break;
            case MCKO:
                sourceType = 11;
                break;
            case FNS_KAZN:
                sourceType = 12;
                break;
            case FNS:
                sourceType = 13;
                break;
            case UFMS:
                sourceType = 14;
                break;
            default:
                sourceType = Integer.MAX_VALUE;
        }

        return mfcDailyStats.stream()
                .map(MfcStatsGroup::getMfcStatsItems)
                .flatMap(Collection::stream)
                .filter(item -> item.getDataSource() == sourceType)
                .collect(Collectors.toList());
    }

    public List<MfcStatsItem> createSortedListOfMfcStatsItems(List<MfcStatsGroup> mfcDailyStats) {
        return mfcDailyStats.stream()
                .map(MfcStatsGroup::getMfcStatsItems)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(MfcStatsItem::getNumber))
                .collect(Collectors.toList());
    }

    public List<MfcStatsItemForEquals> transformForEquals(List<MfcStatsItem> items) {
        List<MfcStatsItemForEquals> result = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            log.info("Transformiing to item for equals " + items.get(i).getName());
            MfcStatsItemForEquals itemForEquals = new MfcStatsItemForEquals();
            itemForEquals.setNumber(items.get(i).getNumber());
            itemForEquals.setName(items.get(i).getName());
            itemForEquals.setDimension(items.get(i).getDimension());
            itemForEquals.setFillingType(items.get(i).getFillingType());
            itemForEquals.setDataSource(items.get(i).getDataSource());

            String value;
            if (items.get(i).getDataSource() != 9) {
                value = items.get(i).getValue().replaceAll("\\s", "")
                        .replaceAll(",", ".");
                if (!value.equalsIgnoreCase("x") && !value.isEmpty()) {
                    Double numericValue = Double.parseDouble(value);
                    value = String.valueOf(numericValue);
                }
            } else {
                value = "";
            }
            itemForEquals.setValue(value);

            String valueYear;
            if (items.get(i).getDataSource() != 9) {
                valueYear = items.get(i).getValueYear().replaceAll("\\s", "")
                        .replaceAll(",", ".");
                if (!valueYear.equalsIgnoreCase("x") && !valueYear.isEmpty()) {
                    Double numericValueYear = Double.parseDouble(valueYear);
                    valueYear = String.valueOf(numericValueYear);
                }
            } else valueYear = "";
            itemForEquals.setValueYear(valueYear);

            String valuePrevYear;
            if (items.get(i).getDataSource() != 9) {
                valuePrevYear = items.get(i).getValuePrevYear().replaceAll("\\s", "")
                        .replaceAll(",", ".");
                if (!valuePrevYear.equalsIgnoreCase("x") && !valuePrevYear.isEmpty()) {
                    Double numericValuePrevYear = Double.parseDouble(valuePrevYear);
                    valuePrevYear = String.valueOf(numericValuePrevYear);
                }
            } else valuePrevYear = "";
            itemForEquals.setValuePrevYear(valuePrevYear);

            result.add(itemForEquals);
        }

        return result;
    }
}