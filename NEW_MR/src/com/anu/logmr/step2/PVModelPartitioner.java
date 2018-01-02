package com.anu.logmr.step2;

import com.anu.logmr.utils.LogBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;



public class PVModelPartitioner extends Partitioner<LogBean, Text> {


    @Override
    public int getPartition(LogBean logBean, Text text, int numPartitions) {

        return (Math.abs((logBean.getIp()+logBean.getRequestDevice()).hashCode()))%numPartitions;
    }
}
