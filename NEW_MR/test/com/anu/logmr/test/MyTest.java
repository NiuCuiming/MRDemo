package com.anu.logmr.test;

import com.anu.logmr.utils.LogBean;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;


public class MyTest {

    @Test
    public void test1() throws ParseException {

        //String str = "GET\t/tag/%E5%88%86%E9%A1%B5/feed/\tHTTP/1.1";
        //int i = str.indexOf('/');  //第一个'/'的位置
        //int j = str.lastIndexOf('H'); //倒数第一个H的位置

        //System.out.println(str.substring(0,i));
        //System.out.println(str.substring(i,j));
        //System.out.println(str.substring(j));

        //时间戳类型转换 18/Sep/2013:07:05:04
        /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf1.format(sdf.parse("18/09/2013:07:05:04"));
        System.out.println(format);*/

        /*String s = "1.162.203.134|2013-09-18 13:47:35|GET|/images/my.jpg|HTTP/1.1|200|19939|http://www.angularjs.cn/A0d9|Mozilla/5.0";
        String[] split = s.split("\\|");
        LogBean logBean = new LogBean(split);
        System.out.println(logBean);*/

        //System.out.println("hahah".hashCode());



        //测试包含表达式
        //String s = "1.202.186.37Mozilla/5.0|0000|1.202.186.37|2013-09-18 15:39:20|GET|/wp-content.gif/uploads/2013/05/favicon.png|HTTP/1.1|200|1150|-|Mozilla/5.0|000";
        //s = "/nodejs-async-windjs";
        //System.out.println(s.matches(""));

        //判断时间间隔
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.parse("2013-09-19 01:57:37");




    }

}
