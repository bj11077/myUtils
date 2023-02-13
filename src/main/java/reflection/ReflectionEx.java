package reflection;

import java.lang.reflect.Method;

public class ReflectionEx {

    public static void main(String[] args) throws Exception{

        Class<?> aClass = Class.forName("somework.Tokenizer");
        Method ddw = aClass.getMethod("ddw");
        Object o = aClass.newInstance();
        ddw.invoke(o);
    }
}
