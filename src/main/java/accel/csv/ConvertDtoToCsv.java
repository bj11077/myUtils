package accel.csv;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertDtoToCsv {

    public static void main(String[] args) throws IOException {
        List<CsvExDto> dtoList = Arrays.asList(
                new CsvExDto("name1", "002", true, 2, Timestamp.valueOf("2022-02-01 00:00:00")),
                new CsvExDto("name2", "003", false, 7, Timestamp.valueOf("2023-02-01 00:00:00")));

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(dtoList);

        System.out.println(jsonString.toString());

        JSONArray docs = new JSONArray(jsonString);

        File file = new File("C:\\\\build\\\\Test.csv");

        String csvString = CDL.toString(docs);
        FileUtils.writeStringToFile(file,csvString);

//        List<String> headers = Arrays.stream(CsvExDto.class.getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
//
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
//
//            for (int i = 0; i < map.keySet().size(); i++) {
//                map.keySet().
//            }
//            for (int i = 0; i < map.size(); i++) {
//                row.createCell(i).setCellValue();
//            }
//        }









    }



}
