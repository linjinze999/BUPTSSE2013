import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IOUtils;

public class getFileCoord {
private Map<String,coord> urlMap = new HashMap<String,coord>();
private int count = 0;
    
    //获取聚点文件对象
    public void initialize(File file) throws IOException {
      BufferedReader in = null;
      try {
        in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        while ((line = in.readLine()) != null) {
          String [] strKey = line.split("\t");
          urlMap.put(strKey[0],parse(line));
          count++;
        }
      } finally {
        IOUtils.closeStream(in);
      }
    }
    
    //通过一行数据生成一个聚点坐标
    public coord parse(String line){
      String [] strPlate = line.split("\t");
      coord Dmurl = new coord(strPlate[0],Integer.parseInt(strPlate[1]),Integer.parseInt(strPlate[2]));
      return Dmurl;
    }
    
    //返回Map数据个数
    public int getCount(){
    	return this.count;
    }
    
    //通过名字查找聚点坐标
    public coord getUrlCode(String cluster){
    	coord returnCode = null;
    	coord dmUrl = (coord)urlMap.get(cluster);
      if(dmUrl == null){
          returnCode = null; 
      }else{
          returnCode =dmUrl;
      }
      return returnCode;
    }
}
