package accel.csv;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.CDL;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertDtoToCsv2 {

    public static void main(String[] args) throws IOException {
        List<CsvExDto> dtoList = Arrays.asList(
                new CsvExDto("name1", "002", true, 2, Timestamp.valueOf("2022-02-01 00:00:00")),
                new CsvExDto("name2", "003", false, 7, Timestamp.valueOf("2023-02-01 00:00:00")));

        List<String> headers = Arrays.stream(CsvExDto.class.getDeclaredFields()).map(Field::getName).collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();

        JSONArray jsonArray = new JSONArray(mapper.writeValueAsString(dtoList));

        File file = new File("C:\\\\build\\\\ss.csv");

        String csvString = CDL.toString(jsonArray);

//        System.out.println(csvString);

        FileUtils.writeStringToFile(file,csvString, Charset.defaultCharset());




//        // row
//        int rowNum = 1;
//
//        //row (가변)
//        XSSFRow row = null;
//
//        // csv
//        XSSFWorkbook workbook = new XSSFWorkbook();
//
//        //sheet
//        XSSFSheet sheet = workbook.createSheet("data");
//
//
//
//
//        // header setting
//        row = sheet.createRow(rowNum);
//        for (int i = 0; i < headers.size(); i++) {
//            row.createCell(i).setCellValue(headers.get(i));
//        }
//
//        // increase num
//        rowNum += 1;
//
//
//        for (CsvExDto dto : dtoList) {
//            ObjectMapper mapper = new ObjectMapper();
//            String str = mapper.writeValueAsString(dto);
//            Map<String,Object> map = mapper.readValue(str, Map.class);
//            row = sheet.createRow(rowNum);
//        }









    }



}
