package com.miaozhen.real;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import com.miaozhen.real.fengxingtest.IntSumReducer;
import com.miaozhen.real.fengxingtest.SplitLogMapper;

public class fengxingtest {

	public static class SplitLogMapper
	extends Mapper<Object, Text, Text, Text>{
		private Text line = new Text();
		private Text line2 = new Text();  
		int sum=0;
		public void map(Object key, Text value, Context context
                 ) throws IOException, InterruptedException {
//	 		FengxingStruck Struck1 = new FengxingStruck();
			boolean TorF;
	 		String logline = new String(value.toString());
		 	String[] linesplice =new String[20];
		 	String FengxingIP=null;
		 	linesplice=logline.split("\\?");
		 	//类型正确
		 	if(linesplice[0].equals("/x.gif")){
		 		linesplice =linesplice[1].split(" +");
		 		//秒针IP
		 		String MiaozhenIP = linesplice[linesplice.length-1];

				linesplice=linesplice[0].split("\\^");
				String[] leftright =new String[2];
				leftright=linesplice[0].split("\\=");
				//地区
				String Zone=leftright[1];
				leftright=linesplice[2].split("\\=");
				//风行IP
				if(leftright.length==2)
					FengxingIP=leftright[1];
					 	
				TorF=IPZone.IPisZone(MiaozhenIP, Zone);
				if(MiaozhenIP.equals(FengxingIP)){
				if(TorF)
					line2.set("E");//same&Both are right
				else
					line2.set("F");//same&Both are wrong
				}
				else
				{
					Boolean FxTorF=IPZone.IPisZone(FengxingIP, Zone);
					if(TorF)
					{
						if(FxTorF)
						line2.set("A");//Both are right
						else
						line2.set("B");//MZ right,FX wrong
					}
					else
					{
						if(FxTorF)
						line2.set("C");//MZ wrong,FX right
						else
						line2.set("D");//Both are wrong
					}
					
				}
//				sum++;
//				System.out.println(sum+FengxingIP+MiaozhenIP);
				line.set(Zone);	
//				line2.set(MiaozhenIP);
				 	context.write(line, line2);
				 }
 }
 
}
public static class IntSumReducer 
extends Reducer<Text,Text,Text,Text> {
	 private Text line = new Text();
	 private Text line2 = new Text();
public void reduce(Text key, Iterable<Text> values, 
                Context context
                ) throws IOException, InterruptedException {
	int sum[]=new int[6];
	//System.out.println(Struck1.getType()+Struck1.getZ()+Struck1.getMd());
	//context.write(key, );
	for(Text temp : values)
	{
//		context.write(key, new Text(temp.toString()));
		switch(temp.toString()){
		case "A":sum[0]++;break;
		case "B":sum[1]++;break;
		case "C":sum[2]++;break;
		case "D":sum[3]++;break;
		case "E":sum[4]++;break;
		case "F":sum[5]++;break;
		default: System.out.println("wrong+"+temp.toString());break;
		}
	}
		sum[0]+=sum[4];
		sum[3]+=sum[5];
		sum[4]+=sum[5];
		int sumALLIP= sum[0]+sum[1]+sum[2]+sum[3];
		int sumDiffIP= sumALLIP-sum[4];
		int sumDiffRigion= sum[1]+sum[2]+sum[3]-sum[5];
        line2.set(""+"+"+sumALLIP+"+"+sumDiffIP+"+"+sumDiffRigion+"+"+sum[0]+"+"+sum[1]+"+"+sum[2]+"+"+sum[3]);
        context.write(key, line2);
}
}

public static void main(String[] args) throws Exception {
Configuration conf = new Configuration();
String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
if (otherArgs.length != 2) {
System.err.println("Usage: wordcount <in> <out>");
System.exit(2);
}
Job job = new Job(conf, "LOGstore");
job.setJarByClass(fengxingtest.class);
job.setMapperClass(SplitLogMapper.class);
//job.setMapOutputValueClass(FengxingStruck.class);
//job.setCombinerClass(IntSumReducer.class);
job.setReducerClass(IntSumReducer.class);
job.setOutputKeyClass(Text.class);
job.setOutputValueClass(Text.class);

FileSystem fs=FileSystem.get(conf); 
Path p=new Path(otherArgs[1]);
if(fs.exists(p)){
	fs.delete(p, true);
	System.out.println("输出路径存在，已删除！");
} 

FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
FileOutputFormat.setOutputPath(job, p);
System.exit(job.waitForCompletion(true) ? 0 : 1);
}
}
