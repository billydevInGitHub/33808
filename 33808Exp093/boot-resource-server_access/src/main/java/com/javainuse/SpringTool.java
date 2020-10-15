package com.javainuse;

import billydev.SpringToolConfiguration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public class SpringTool {

    private static int count=0;
    private static StringBuilder sb = new StringBuilder();
    static class Inner implements BiConsumer<String,Object> {

        /**
         * Performs this operation on the given arguments.
         *
         * @param k the first input argument
         * @param v the second input argument
         */
        @Override
        public void accept(String k, Object v) {
            count++;
            sb.append(count + " "+k + " ~~~~~~~~~~~~~~~~ \r\n" + v.toString()+"\r\n\r\n");
        }
    }
    public static String printBeanDefinitionMap(ConcurrentHashMap beanDefinitionMap){
// beanDefinitionMap.forEach((key, value) -> {count++; sb.append(count++ + " "+key.toString() + " ~~~~~~~~~~~~~~~~ \r\n" + value.toString()+"\r\n\r\n")});
        beanDefinitionMap.forEach(new Inner());
        String returnString=sb.toString();
        sb=new StringBuilder();
        SpringToolConfiguration.test();
        return returnString;
    }

}
