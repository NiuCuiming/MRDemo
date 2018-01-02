package com.anu.logmr.step2;

import com.anu.logmr.step1.OrderRun;
import com.anu.logmr.utils.GroupComparator;
import com.anu.logmr.utils.LogBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class PVModelRun {

    public static void main(String[] args) {

        Configuration config = new Configuration();

        config.set("fs.defaultFS", "file://localhost");
        config.set("mapreduce.framework.name", "local");

        try{

            FileSystem fs = FileSystem.get(config);
            Job job = Job.getInstance(config);
            job.setJarByClass(OrderRun.class);

            job.setJobName("OrderLog");
            job.setMapperClass(PVModelMapper.class);
            job.setReducerClass(PVModelReducer.class);
            job.setPartitionerClass(PVModelPartitioner.class);
            //job.setGroupingComparatorClass(GroupComparator.class);
            //job.setNumReduceTasks(10);

            job.setMapOutputKeyClass(LogBean.class);
            job.setMapOutputValueClass(LogBean.class);

            job.setOutputValueClass(LogBean.class);
            job.setOutputValueClass(NullWritable.class);

            FileInputFormat.addInputPath(job,new Path("C:/MR/access-log/output"));
            Path outpath = new Path("C:/MR/access-log/output2");

            if(fs.exists(outpath)) {
                fs.delete(outpath,true);
            }

            FileOutputFormat.setOutputPath(job, outpath);

            boolean f= job.waitForCompletion(true);

            if(f){
                System.out.println("job任务结束");
            }else {
                System.out.println("执行失败！");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
