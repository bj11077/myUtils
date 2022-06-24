import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Locale;


// 미완
public class ConvertAccelSet {

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("C:\\accelExport\\CVAS.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheetAt(4);


//        int totalRows = 732;
//
//        int rowNo = 0;
//        int cellIndex = 0;

        //TOPIC명
        XSSFRow topicRow = sheet.getRow(4);




        String topicNm = "";
        String mulNm = "";
        String typeNm = "";

        XSSFRow row;
        XSSFCell topicVal;
        XSSFCell mulVal;
        XSSFCell typeVal;
        File tmp = null;
        BufferedWriter bw = null;
        String fir = "";
        String sec = "";
        // topic 10  물리 11 데이터타입 13
        for(int i = 2; i<=sheet.getPhysicalNumberOfRows();i++){
            row = sheet.getRow(i);
            topicVal = row.getCell(11);
            if(!topicNm.equals(topicVal.getStringCellValue())){
                if(i != 2){
                    bw.close();
                }
                tmp = new File("C:\\convert\\after\\"+topicVal.getStringCellValue()+".txt");
                bw = new BufferedWriter(new FileWriter(tmp));
            }
            //물리항목명
            mulVal = row.getCell(13);
            mulNm = mulVal.getStringCellValue();
//            System.out.println("물리항목명 = " + mulNm);
            //토픽명
            topicNm = topicVal.getStringCellValue();
            //데이터 타입
            typeVal = row.getCell(15);


            //데이터타입분기
            if(typeVal.getStringCellValue().contains("bigint")){
                typeNm = "BigInteger.valueOf(100)";
            }else if(typeVal.getStringCellValue().contains("DECI")){
                typeNm = "0.0";
            }else if(typeVal.getStringCellValue().contains("int")){
                typeNm = "0";
            }else if(typeVal.getStringCellValue().contains("varchar")){
                typeNm = "\"exam\"";
            }else if(typeVal.getStringCellValue().contains("ENUM")){
                typeNm = "\"exam\"";
            }
            // 2.1. 첫번째 글자 substring
            try {
                fir = mulNm.substring(0, 1).toUpperCase();
                bw.write("dto.set" + fir + mulNm.substring(1) + "("+typeNm+");");
                bw.newLine();
            }catch (Exception e){

            }


       }





    }
}
