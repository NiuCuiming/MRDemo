package com.anu.logmr.step2;

import com.anu.logmr.utils.LogBean;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 目的：
 *  按照时间同一用户，访问时间处于10分钟之内为一个session
 *  为每个session赋予唯一编号：（1.如果只有一个reduce，则定义全局变量 2.多个session则按照用户名加session号）
 */
public class PVModelReducer extends Reducer<LogBean,LogBean,LogBean,NullWritable> {

    //时间解析器 和 解析结果变量：
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Date parse = null;

    //session编号
    public static String sessionPre = null;
    public static int sessionID = 1;

    //步骤编号
    public static int step = 1;

    //起始时间，结束时间，时间间隔
    public static Date bgtime = null;
    public static Date edtime = null;
    public static long timeInterval = 0;



    @Override
    protected void reduce(LogBean key, Iterable<LogBean> values, Context context) throws IOException, InterruptedException {

        //初始化
        sessionPre = key.getUser()+"__";
        step = 1;

        for (LogBean logbean: values) {

            //session的第一条日志记录
            if(step == 1) {
                sessionID = 1;
                try {

                    //解析时间
                    bgtime = sdf.parse(logbean.getRequestTimestamp());
                    logbean.setStep(step+""); step++;
                    logbean.setSessionId(sessionPre+sessionID);
                    context.write(logbean,NullWritable.get());

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {

                //解析后面的日志
                try {

                    edtime = sdf.parse(logbean.getRequestTimestamp());

                    //判断时间间隔(minute)
                    timeInterval = ((edtime.getTime() - bgtime.getTime()) / 60000);
                    if (timeInterval < 10) {

                        logbean.setStep(step + "");
                        step++;
                        logbean.setSessionId(sessionPre + sessionID);
                        context.write(logbean,NullWritable.get());

                    } else {

                        step = 1;
                        sessionID++;
                        bgtime = sdf.parse(logbean.getRequestTimestamp());
                        logbean.setStep(step + "");
                        step++;
                        logbean.setSessionId(sessionPre + sessionID);
                        context.write(logbean,NullWritable.get());
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /*@Override
    protected void reduce(LogBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        //session编号
        String sessionPre = key.getIp()+key.getRequestDevice();
        int sessionID = 0;

        //步骤编号
        int step = 0;

        for (Text timeStampe : values) {

            //解析时间
            try {
                parse = sdf.parse(values.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            context.write(new Text(key.toString()),NullWritable.get());
        }

    }*/
}
