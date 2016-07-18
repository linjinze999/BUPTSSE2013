import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class KMeans {
	static int K = 5;											//聚类数
	static int iteration_number =100;		//最大迭代次数
	static double accuracy = 0;				//精度
	static int over  = 0;									//是否所有聚类中心符合精度
	static int[] center = new int[K];		//初始聚类中心的位置
	static getFileCoord drp ;						//用于读取初始聚类中心
	static List<coord> oldCenter = new ArrayList<coord>(K);	//旧聚类中心
	static List<coord> newCenter = new ArrayList<coord>(K);	//新聚类中心
		
	public static class KmeansMapper  extends Mapper<LongWritable, Text, Text, Text> 
	{
private coord record0 = new coord();
private coord record1 = new coord();
private static double Min_distance = 9999;
private int tmpK = 0;
private Text tKey = new Text();
private Text tValue = new Text();

//根据聚类坐标，把文件中的点进行类别划分
public void map(LongWritable key, Text value,Context output)
        throws IOException, InterruptedException {
    String [] strArr = value.toString().split("\n");
    String[] line;
    double tempx = 0;
    double tempy = 0;
    //将所有点分类
    for(int n=0;n<strArr.length;n++){
    	Min_distance = 9999;
    	line = strArr[n].split("\t");
    	record1.setName(line[0]);
        record1.setX(Double.parseDouble(line[1]));
        record1.setY(Double.parseDouble(line[2]));
        //聚类到最近点
    	for(int i=1; i <= K; i++){
            record0 = oldCenter.get(i-1);
            if(record0.getDistance(record1) < Min_distance){
                tmpK = i;
                tempx = record0.getX();
                tempy = record0.getY();
                Min_distance = record0.getDistance(record1);
            }
        }
    	//输出
    	tKey.set("C"+tmpK+"\t"+tempx+"\t"+tempy);
    	tValue.set(record1.getName()+"\t"+record1.getX()+"\t"+record1.getY());
        output.write(tKey, tValue);
    }
}
	}
	//计算新的聚类中心
	public static class KmeansReducer extends Reducer<Text, Text, Text, Text> {
	private Text tKey = new Text();
	private Text tValue = new Text();

	public void reduce(Text key,  Iterable<Text> value,Context output)
	        throws IOException, InterruptedException {
	    double avgX=0;
	    double avgY=0;
	    double sumX=0;
	    double sumY=0;
	    int count=0;
	    String [] strValue = null;
	    String  coords="";
	    //整合此聚类的所有坐标
	    for(Text one_value : value){
	    	strValue = one_value.toString().split("\t");
	    	Double.valueOf(strValue[1]).doubleValue();
	    	sumX = sumX + Double.valueOf(strValue[1]).doubleValue();
	        sumY = sumY + Double.valueOf(strValue[2]).doubleValue();
	        coords+=strValue[0]+"["+strValue[1]+","+strValue[2]+"];";
	        count++;
		 }
	  //获取聚类中心的新坐标
	    avgX = sumX/count;
	    avgY = sumY/count;
	    //比较新旧聚类中心是否符合精度,并提交新聚类中心
	    String[] s = key.toString().split("\t");
	    int cc = Integer.parseInt(key.toString().split("\t")[0].substring(1));
	    coord oc = new coord("OC",Double.parseDouble(s[1]),Double.parseDouble(s[2]));
	    coord nc = new coord("p"+cc,avgX,avgY);
	    newCenter.set(cc-1, nc);
	    if(oc.getDistance(nc)<=accuracy)
	    	over ++;
	    //输出
	    DecimalFormat df = new DecimalFormat("######0.00");
	    tKey.set("K"+key.toString().split("\t")[0].substring(1)+"["+df.format(avgX )+ "," + df.format(avgY)+"]");
	    tValue.set(coords+"");
	    output.write(tKey, tValue);
	}
	}
	
	public static void main(String[] args) throws Exception {
		configure();
		int run_count = 0;
		for(int i = 0;i<K;i++){
			coord co = new coord("p1",0,0);
			oldCenter.add(co);
			newCenter.add(co);
		}
		//设置初始聚类中心
		setCenter();
		while(iteration_number > 0 && over<K)
		{
			over = 0;
			//若输出路径存在,删去该文件夹
			File file = new File("/home/linjinze/Result/a");
			DeleteFile(file);
			
			//新建Job
			Configuration configuration = new Configuration();
	        Job job = new Job(configuration,"Kmeans");
	        job.setJarByClass(KMeans.class);
	        // 设置Map输出的key和value的类型
	        job.setMapOutputKeyClass(Text.class);
	        job.setMapOutputValueClass(Text.class);
	     	// 设置Reduce输出的key和value的类型
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(Text.class);
	     	// 设置Mapper和Reducer
	        job.setMapperClass(KmeansMapper.class);
	        job.setReducerClass(KmeansReducer.class);
	     	// 设置输入输出目录
	        Path DocumentCollection = new Path("/home/linjinze/cluster.txt");//输入文件路径
	        Path PostingList = new Path("/home/linjinze/Result/a");//输出文件路径
	        FileInputFormat.addInputPath(job, DocumentCollection);
	        FileOutputFormat.setOutputPath(job, PostingList);
			//等待结束
	        job.waitForCompletion(true);
	        
	        //将新聚类中心赋给旧聚类中心
	        resetCenter();
	        
			iteration_number--;
			run_count++;
		}
		System.out.println("本次聚类共迭代"+run_count+"次");
		System.exit(1);
		}
	
	//设置初始聚类中心
	private static void setCenter() {
		// TODO Auto-generated method stub
		int count = 0;
	    int flag = 0;
		Random rdm = new Random(System.currentTimeMillis());
	    int r =  Math.abs(rdm.nextInt());
	    while(count<K)
	    {
	     	flag = 0;
	    	center[count] = r % drp.getCount();
	    	r = (center[count]+1)*157;
	    	for(int i = 0;i<count;i++)
	    		if(center[count]==center[i])
	    			flag = 1;
	    	if(flag==0)
	    		count++;
	    }
	    for(int i=0;i<K;i++)
	    	oldCenter.set(i, drp.getUrlCode("p"+center[i]+""));
	}

	//删除文件夹
	private static void DeleteFile(File file) {
		// TODO Auto-generated method stub
		if(file.exists()){
			if(file.isFile())
				file.delete();
			else if(file.isDirectory()){
				File files[] = file.listFiles();
				for(int i=0;i<files.length;i++)
					DeleteFile(files[i]);
				file.delete();
			}
		}
	}

	//用于获取聚类中心坐标
			public static void configure() {
			   drp = new getFileCoord();
			    try {
			        drp.initialize(new File("/home/linjinze/cluster.txt"));
			    } catch (IOException e) {
			        throw new RuntimeException(e);
			   }
			}
	//重设聚类中心位置
	private static void resetCenter() {
		for(int i=0;i<K;i++)
			oldCenter.set(i, newCenter.get(i));
	}
}
