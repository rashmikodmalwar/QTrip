package qtriptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class DP { 
   
    @DataProvider(name ="logindata")
    public static Object[][] getExcelData(Method m) throws IOException{
    
        String filepath = "./src/test/resources/DatasetsforQTrip.xlsx";
        File path = new File(filepath);
        FileInputStream inpustStream = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(inpustStream);
        XSSFSheet sheet = workbook.getSheet(m.getName());
        int rowcount =sheet.getPhysicalNumberOfRows();
        int colsCount = sheet.getRow(0).getLastCellNum();
        Object[][] arrayExcelData = new Object[rowcount-1][colsCount-1];
        DataFormatter formatter = new DataFormatter();
        for(int outer = 1;outer<rowcount;outer++){
            XSSFRow rows = sheet.getRow(outer);
            for(int inner = 1;inner<colsCount;inner++){
                XSSFCell cell = rows.getCell(inner);
                
                   arrayExcelData[outer-1][inner-1] =formatter.formatCellValue(cell);
                   
            }
        }
        workbook.close();
        inpustStream.close();
     return arrayExcelData;
        
    }
}
