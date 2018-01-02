package com.anu.logmr.step2;

import com.anu.logmr.utils.LogBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 功能：把step1清洗好的数据继续清洗成客户点击流模型的数据。
 *   1. 使用logBean解析行
 *   2. Mapper输出user , logBean
 *   3. 清洗掉包含.js .gif .css .png .ico
 */
public class PVModelMapper extends Mapper<LongWritable,Text,LogBean,LogBean> {

    public LogBean logBean;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] split = value.toString().split("\\|");

        if(split.length < 9) {
            return;
        }else if (split[3].contains(".js") || split[3].contains(".css")
                || split[3].contains(".png") || split[3].contains(".gif")
                || split[3].contains(".ico")) {
            return;
        } else {
            logBean = new LogBean(split);
        }

        context.write(logBean,logBean);
    }
}
