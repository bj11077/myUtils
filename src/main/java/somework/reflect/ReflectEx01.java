package somework.reflect;

import somework.Tokenizer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReflectEx01 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {


        Class<Tokenizer> tokenizerClass = Tokenizer.class;

        Constructor<Tokenizer> declaredConstructor = tokenizerClass.getDeclaredConstructor();

        Method dd = tokenizerClass.getMethod("dd");
        Tokenizer tokenizer = declaredConstructor.newInstance();
        System.out.println("fdgkdmf");
        dd.invoke(tokenizer);



        String name = tokenizerClass.getName();
        System.out.println(name);
        Method[] methods = tokenizerClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }


    }
}
