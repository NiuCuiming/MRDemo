package com.anu.logmr.test;

import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyTest {

    @Test
    public void test1() throws ParseException {

        String str = "GET\t/tag/%E5%88%86%E9%A1%B5/feed/\tHTTP/1.1";

        int i = str.indexOf('/');  //第一个'/'的位置
        int j = str.lastIndexOf('H'); //倒数第一个H的位置

//        System.out.println(str.substring(0,i));
//        System.out.println(str.substring(i,j));
//        System.out.println(str.substring(j));

        //时间戳类型转换 18/Sep/2013:07:05:04
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf1.format(sdf.parse("18/09/2013:07:05:04"));
        System.out.println(format);

    }

}
