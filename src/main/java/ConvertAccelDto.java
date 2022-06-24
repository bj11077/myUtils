import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ConvertAccelDto {

    /*
    문제
    
    중간에 공백뜨면 처리가안됨
     */
    
    public static void main(String[] args) throws IOException {

        // io
        FileInputStream file = new FileInputStream("C:\\convert\\before\\before.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        // 시트 5번째꺼를 쓰겟다!   ( 시트 0번부터 시작 )
        XSSFSheet sheet = workbook.getSheetAt(4);



        //  토탈로우   0부터라 -1해야댐   getPhysicalNumberOfRows가 안쓰는 행도 포함해서 숫자이상하게나옴
        int totalRows = 696;
//        int totalRows = sheet.getPhysicalNumberOfRows();

       
        //토픽명
        String topicNm = "";
        
        //물리이름
        String mulNm = "";
        
        //데이터타입
        String dataType = "";

        // snake to camel   data_data -> dataData
        int splitCnt = 0;
        StringBuilder sb = new StringBuilder();

        // 열 (한줄) 값
        XSSFRow row;
        
        // XSSRow에서 특정 행값 가져오기   굳이 여러개쓸필요없어보이는대 나중에지워야지
        XSSFCell topicVal;
        XSSFCell mulVal;
        XSSFCell typeVal;

        //저장할 파일
        File saveFile = null;
        BufferedWriter bw = null;

        // topic 10  물리 11 데이터타입 13
        for(int i = 2; i<=totalRows;i++){
            // i번째열
            row = sheet.getRow(i);

            topicVal = row.getCell(11);
            
            // topicNm이 바뀌면 다른 테이블이니까 버퍼닫고 다른 파일로 다시염
            //  getStringCellValue를 통해 셀의 값을 가져옴
            if(!topicNm.equals(topicVal.getStringCellValue())){
                if(i != 2){
                    bw.close();
                }
                saveFile = new File("C:\\convert\\after\\"+topicVal.getStringCellValue()+".txt");
                bw = new BufferedWriter(new FileWriter(saveFile));
            }
            //
            mulVal = row.getCell(13);
            mulNm = mulVal.getStringCellValue();
//            System.out.println("물리항목명 = " + mulNm);
            //
            topicNm = topicVal.getStringCellValue();
            //
            typeVal = row.getCell(15);



            //데이터타입분기
            if(typeVal.getStringCellValue().contains("bigint")){
                dataType= "BigInteger";
            }else if(typeVal.getStringCellValue().contains("DECI")){
                dataType= "long";
            }else if(typeVal.getStringCellValue().contains("int")){
                dataType= "int";
            }else if(typeVal.getStringCellValue().contains("varchar")){
                dataType= "String";
            }else if(typeVal.getStringCellValue().contains("ENUM")){
                dataType= "String";
            }
            // 데이터 저장
            try {
                // 카멜변환
                String[] mulArray = mulNm.split("_");
                for(String mul : mulArray){
                    if(splitCnt != 0){
                            sb.append(mul.substring(0,1).toUpperCase() + mul.substring(1));
                    // 1일때
                    }else{
                            sb.append(mul);
                            splitCnt++;
                    }
                }


                bw.write("private "+dataType +" "+ sb.toString() + ";");
                bw.newLine();
                sb.setLength(0);
                splitCnt = 0;
            }catch (Exception e){

            }
       }
        bw.close();





    }
}
