package com.baidu;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 2018/4/14.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        WeakHashMap<String,Class> FileClassMap = new WeakHashMap<>();
        Enumeration<URL> enumeration = Thread.currentThread().getContextClassLoader().getResources("");
        while (enumeration.hasMoreElements()){
            URL url = enumeration.nextElement();
            System.out.println(url);
        }
    }
}
