package Program;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class InvertedIndex {
	public static class InvertedIndexMapper extends Mapper<Object, Text, Text, Text >{//拆分:<单词及文件地址,1>
		private Text value_one = new Text("1");
		private Text key_WordAndURL = new Text();
		private FileSplit split;
		
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException{
			split = (FileSplit) context.getInputSplit();
			StringTokenizer my_value = new StringTokenizer(value.toString());
			while (my_value.hasMoreElements()) {
				key_WordAndURL.set(my_value.nextToken()+"===>"+split.getPath().toString());
                context.write(key_WordAndURL, value_one);
            }
		}
	}
	
	public static class InvertedIndexCombiner extends Reducer<Text,Text,Text,Text>{//中间结果合并:每个文件的词频
		public void reduce (Text key, Iterable<Text> values,Context contex) throws IOException, InterruptedException{
			 int sum = 0;
			 Text value_URLAndCount = new Text();
			 for(Text one_value : values){
				 sum += Integer.parseInt(one_value.toString());
			 }
			 //将单词和文件地址分开
			 int word = key.toString().indexOf("===>");
			 //设置value值:文件地址===>词频
			 value_URLAndCount.set(key.toString().substring(word+4) + "===>" +sum);
			 //设置key值:单词
			 key.set(key.toString().substring(0,word));
			 contex.write(key,  value_URLAndCount);
		}
	}
	
	public static class InvertedIndexReducer extends Reducer<Text,Text,Text,Text>{//结果合并:所有文件及词频
		private Text result = new Text();
		public void reduce(Text key, Iterable<Text> values,Context contex) throws IOException, InterruptedException{
			String PostingList = new String();
			for (Text value : values) {
				PostingList += value.toString()+";";
            }
			result.set(PostingList);
            contex.write(key, result);
		}
	}
	
	public static class InvertedIndexOutputFileName extends MultipleOutputFormat<Text, Text> { //利用hash算法设置输出文件名
	      @Override  
	      protected String generateFileNameForKeyValue(Text key, Text value, Configuration conf) {  
	          char c = key.toString().toLowerCase().charAt(0);  
	          if (c >= 'a' && c <= 'z') {  //hash算法内容:输出文件为以单词首字母命名的txt文件,其他情况为other.txt文件
	              return c + ".txt";  
	          }  
	          return "other.txt";  
	      }  
	  }
	
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
	        
	        Configuration configuration = new Configuration();
	        
	        Job job = new Job(configuration,"InvertedIndex");
	        
	        job.setJarByClass(InvertedIndex.class);
	        
	        job.setMapperClass(InvertedIndexMapper.class);
	        job.setMapOutputKeyClass(Text.class);
	        job.setMapOutputValueClass(Text.class);
	        
	        job.setCombinerClass(InvertedIndexCombiner.class);
	        
	        job.setReducerClass(InvertedIndexReducer.class);
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(Text.class);
	         job.setOutputFormatClass(InvertedIndexOutputFileName .class);//输出格式
	        
	        Path DocumentCollection = new Path("./src/DocumentCollection/");//输入文件路径
	        Path PostingList = new Path("./src/PostingList/PL/");//输出文件路径
	        FileInputFormat.addInputPath(job, DocumentCollection);
	        FileOutputFormat.setOutputPath(job, PostingList);
	        
	        System.exit(job.waitForCompletion(true)?0:1);
	        
	        
	     }

}
