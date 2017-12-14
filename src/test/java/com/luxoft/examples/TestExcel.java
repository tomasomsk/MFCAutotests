package com.luxoft.examples;

import com.luxoft.mfcautotests.model.MfcStatsGroup;
import com.luxoft.mfcautotests.model.MfcStatsItem;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class TestExcel {

    public static void main(String[] args) {
        List<MfcStatsGroup> mfcDailyStats = new ArrayList<>();
        try {
            File statsFile = new File("src/main/resources/excel/statistic/dailyStatsItems.xlsx");
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
//                    mfcStatsItem.setFillingType(getValueFromCell(fillingTypeCell));
//                    mfcStatsItem.setDataSource(getValueFromCell(dataSourceCell));
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

            long count = mfcDailyStats.stream()
                    .map(MfcStatsGroup::getMfcStatsItems)
                    .flatMap(Collection::stream)
                    .peek(item -> System.out.println(item.getNumber() + " " + item.getName() + " " + item.getValue() + item.getIsDashboard()))
                    .count();

            System.out.println(count);

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public static String getValueFromCell(XSSFCell cell) {

       return Objects.nonNull(cell) ? cell.getCellTypeEnum().equals(CellType.NUMERIC)
                ? String.valueOf(cell.getNumericCellValue()) : cell.getStringCellValue() : "";
    }
}