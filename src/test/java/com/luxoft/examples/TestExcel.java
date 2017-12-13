package com.luxoft.examples;

import com.luxoft.mfcautotests.model.MfcStatsItem;
import com.luxoft.mfcautotests.model.MfcStatsGroup;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TestExcel {


    public static void main(String[] args) {
        List<MfcStatsGroup> mfcDailyStats = new ArrayList<>();
        try {
            FileInputStream statsInputStream = new FileInputStream("src/main/resources/excel/statistic/dailyStatsItems.xlsx");
            XSSFWorkbook mfcStats = new XSSFWorkbook(statsInputStream);
            XSSFSheet mfcStatsSheet = mfcStats.getSheetAt(0);
            Iterator<Row> rows = mfcStatsSheet.rowIterator();
            int i = 0;
            rows.next();
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();

                XSSFCell sectionNameCell = row.getCell(0);
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

                if (sectionNameCell != null && !"".equals(sectionNameCell.getStringCellValue())) {
                    i += 1;
                    MfcStatsGroup mfcStatsGroup = new MfcStatsGroup();
                    String sectionName = sectionNameCell.getStringCellValue();
                    mfcStatsGroup.setName(sectionName);
                    mfcDailyStats.add(mfcStatsGroup);
                }

                if (mfcStatsItemNameCell != null) {
                    MfcStatsItem mfcStatsItem = new MfcStatsItem();
                    mfcStatsItem.setName(mfcStatsItemNameCell.getStringCellValue());

                    if (numberCell != null) {
                        mfcStatsItem.setNumber((int) numberCell.getNumericCellValue());
                    }
                    if (calculationLogicAccountingPeriodCell != null) {
                        mfcStatsItem.setCalculationLogicAccountingPeriod(calculationLogicAccountingPeriodCell.getStringCellValue());
                    } else {
                        mfcStatsItem.setCalculationLogicAccountingPeriod("");
                    }
                    if (calculationLogicCurrentYearCell != null) {
                        mfcStatsItem.setCalculationLogicCurrentYear(calculationLogicCurrentYearCell.getStringCellValue());
                    } else {
                        mfcStatsItem.setCalculationLogicCurrentYear("");
                    }
                    if (calculationLogicLastYearCell != null) {
                        mfcStatsItem.setCalculationLogicLastYear(calculationLogicLastYearCell.getStringCellValue());
                    } else {
                        mfcStatsItem.setCalculationLogicLastYear("");
                    }
                    if (dimensionCell != null) {
                        mfcStatsItem.setDimension(dimensionCell.getStringCellValue());
                    } else {
                        mfcStatsItem.setDimension("");
                    }
                    if (fillingTypeCell != null) {
                        mfcStatsItem.setFillingType(fillingTypeCell.getStringCellValue());
                    } else {
                        mfcStatsItem.setFillingType("");
                    }
                    if (dataSourceCell != null) {
                        mfcStatsItem.setDataSource(dataSourceCell.getStringCellValue());
                    } else {
                        mfcStatsItem.setDataSource("");
                    }
                    if (itemCodeCell != null) {
                        mfcStatsItem.setItemCode((int) itemCodeCell.getNumericCellValue());
                    }
                    if (isDashboardCell != null) {
                        String isDashboard = isDashboardCell.getStringCellValue();
                        if (isDashboard.equalsIgnoreCase("Да")) {
                            mfcStatsItem.setIsDashboard("Y");
                        }
                    } else {
                        mfcStatsItem.setIsDashboard("");
                    }

                    if (itemIdPeriodCell != null) {
                        String valueFromCell = getValueFromCell(itemIdPeriodCell);
                        mfcStatsItem.setItemIdPeriod(valueFromCell);
                    } else {
                        mfcStatsItem.setItemIdPeriod("");
                    }

                    if (itemIdYearCell != null) {
                        String valueFromCell = getValueFromCell(itemIdYearCell);
                        mfcStatsItem.setItemIdYear(valueFromCell);
                    } else {
                        mfcStatsItem.setItemIdYear("");
                    }

                    if (itemIdPrevYearCell != null) {
                        String valueFromCell = getValueFromCell(itemIdPrevYearCell);
                        mfcStatsItem.setItemIdPrevYear(valueFromCell);
                    } else {
                        mfcStatsItem.setItemIdPrevYear("");
                    }

                    if (parameterCell != null) {
                        mfcStatsItem.setParameter(parameterCell.getStringCellValue());
                    } else {
                        mfcStatsItem.setParameter("");
                    }

                    if (mfcStatsItem.getName().startsWith(" ")) {
                        mfcDailyStats.get(i - 1).getMfcStatsItems().get(mfcDailyStats.get(i - 1).getMfcStatsItems().size() - 1).addSubItem(mfcStatsItem);
                    } else {
                        mfcDailyStats.get(i - 1).addMfcStatsItem(mfcStatsItem);
                    }
                }
            }

            int sum = 0;
            for (int k = 0; k < mfcDailyStats.size(); k++) {
                MfcStatsGroup mfcStatsGroup = mfcDailyStats.get(k);
                List<MfcStatsItem> items = mfcStatsGroup.getMfcStatsItems();
                for (int j = 0; j < items.size(); j++) {
                    System.out.print(items.get(j).getNumber() + " ");
                    System.out.println(items.get(j).getName());
                    if (!items.get(j).getSubItem().isEmpty()) {
                        for (int p = 0; p < items.get(j).getSubItem().size(); p++) {
                            System.out.println(items.get(j).getSubItem().get(p).getName());
                            sum += 1;
                        }
                    }
                }
                sum += items.size();
            }
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValueFromCell(XSSFCell cell) {
        if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return cell.getStringCellValue();
        }
    }
}