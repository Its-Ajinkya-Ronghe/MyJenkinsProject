package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    public static List<String[]> getLoginData(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();
            rows.next(); // skip header
            while(rows.hasNext()) {
                Row row = rows.next();
                String username = row.getCell(0).getStringCellValue();
                String password = row.getCell(1).getStringCellValue();
                String status   = row.getCell(2).getStringCellValue();
                data.add(new String[]{username, password, status});
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}