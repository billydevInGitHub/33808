package com.billydev.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public class SpringTool {

    private static int count=0;
    private static StringBuilder sb = new StringBuilder();
    static class  Inner implements BiConsumer<String,Object>{

        /**
         * Performs this operation on the given arguments.
         *
         * @param k the first input argument
         * @param v the second input argument
         */
        @Override
        public void accept(String k, Object v) {
            count++;
            sb.append(count + "  "+k + "  ~~~~~~~~~~~~~~~~  \r\n" + v.toString()+"\r\n\r\n");
        }
    }
    public static String printBeanDefinitionMap(ConcurrentHashMap beanDefinitionMap){
//      beanDefinitionMap.forEach((key, value) -> {count++; sb.append(count++ + "  "+key.toString() + "  ~~~~~~~~~~~~~~~~  \r\n" + value.toString()+"\r\n\r\n")});
        beanDefinitionMap.forEach(new Inner());
        return sb.toString();
    }
    public static String printObject(Object o){
        return o.toString();
    }

    public static String printAnything(){
        return "###";
    }
}
