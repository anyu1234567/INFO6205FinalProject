package sort;

import sort.huskySort.util.LazyLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class GetUnsortedArray {
    public static LazyLogger logger = new LazyLogger(GetUnsortedArray.class);
    public static String[] get(){
        String path = "src/main/resources/shuffledChinese.txt";
        try {
            ArrayList<String> arrayList = new ArrayList<String>();
            String encoding="UTF-8";
            File file=new File(path);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    arrayList.add(lineTxt);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
            String[] strary = arrayList.toArray(new String[0]);
            return  strary;
        }catch (Exception e){
            logger.info("读取"+path+"文件失败");
            return null;
        }

    }
    public static String[] get(String size){
        String substring = size.substring(0, size.length());
        double parseDouble = Double.parseDouble(substring);
        double v=0d;
        if (size.charAt(size.length())=='K'||size.charAt(size.length())=='k'){
            v = parseDouble / 10.5 / 1024*999998;
        }else {
            v = parseDouble / 10.5 *999998;
        }

        String[] results = new String[(int) v];
        System.arraycopy(get(),0,results,0,(int)v);
        return results;
    }
}
