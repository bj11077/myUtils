package ppt.jsonconvert;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertJsonToDto {

    public static void main(String[] args) throws IOException {



        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\convert\\file\\before.txt"));

        StringBuilder sb = new StringBuilder();
        String line;
        while((line = bufferedReader.readLine()) != null){
            sb.append(line);
        }

        String[] strArr = sb.substring(1,sb.toString().length()-1).split(",");

        List<String> list = Arrays.asList(strArr);

        Map<String,String> result = new HashMap<>();



        list.forEach(e->{
            String key = e.split(":")[0].substring(1,e.split(":")[0].length()-1);
            String value = typeSelect(e.split(":")[1]);
            result.put(key,value);
        });

        File saveFile = new File("C:\\convert\\file\\after.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile));

        for(String a : result.keySet()){
            System.out.println(a+" : "+result.get(a));
            bw.write("private "+ result.get(a)+" "+ a+";");
            bw.newLine();
        }
        bw.close();
    }

    public static String typeSelect(String splitValue){
        String result;

        if(splitValue.contains("\"")){
            result = "String";
        }else if(splitValue.contains(".")){
            result = "float";
        }else{
            result = "int";
        }
        return result;
    }


}
