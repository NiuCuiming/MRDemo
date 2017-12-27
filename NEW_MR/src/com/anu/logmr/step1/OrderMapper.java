package com.anu.logmr.step1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 第一步规整清洗：
 * 清洗结果：
 * 116.24.8.156	18/Sep/2013:07:05:04	GET	 /wp-content/uploads/2013/05/favicon.ico	HTTP/1.1	200	1150	-	"Mozilla/5.0	(Macintosh;	Intel	Mac	OS	X	10_8_4)	AppleWebKit/537.36	(KHTML,	like	Gecko)	Chrome/29.0.1547.65	Safari/537.36"
 */
public class OrderMapper extends Mapper<LongWritable,Text,Text,NullWritable>{

    public String[] split;  //接收切分好的字段
    public String ip;               //来访
    public String requestTimestamp; //请求时间戳
    public String requestType;      //请求类型
    public String requestPath;      //请求路径
    public String requestProtocol;  //请求协议
    public String respondCode;      //响应码
    public String stayTime;         //停留时间
    public String referalUrl;       //指引Url
    public String requestDevice;    //请求设备

    public Text resultLine = new Text();


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        /*
        1.按空格切分
         */
        split = value.toString().replace("\"","").split(" ");

        /*
        2.如果长度等于10
         */
        if(split.length > 11 ) {

            //1.来访IP，响应码，停留时间，指引路径，请求设备 直接赋值
            ip = split[0];
            respondCode = split[8];
            stayTime = split[9];
            referalUrl = split[10];
            requestType = split[5];
            requestPath = split[6];
            requestProtocol = split[7];

            //2.获取时间戳，去掉'[18/Sep/2013:07:05:04' 的 '[',解析格式
            try {
                requestTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss").
                        parse(split[3].substring(1).replaceAll("Sep","09")));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //请求设备
            requestDevice = split[11];   //最后几个字段结合

            //连接
            resultLine = new Text(ip+"|"+requestTimestamp+"|"+requestType+"|"+requestPath+"|"+requestProtocol+"|"+
                    respondCode+"|"+stayTime+"|"+referalUrl+"|"+requestDevice);
        }else {
            return;
        }

        context.write(resultLine,NullWritable.get());

    }
}
