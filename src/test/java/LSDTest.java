
import com.google.common.io.Files;
import org.junit.Test;
import sort.PinyinHelper;
import sort.counting.LSDStringSort;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class LSDTest {
   @Test
   public void Test1() throws IOException {
//      ArrayList<String> arrayList = new ArrayList<String>();
//      String encoding="UTF-8";
//      File file=new File("src/main/resources/shuffledChinese.txt");
//      if(file.isFile() && file.exists()){ //判断文件是否存在
//         InputStreamReader read = new InputStreamReader(
//                 new FileInputStream(file),encoding);//考虑到编码格式
//         BufferedReader bufferedReader = new BufferedReader(read);
//         String lineTxt = null;
//         while((lineTxt = bufferedReader.readLine()) != null){
//            arrayList.add(lineTxt);
//         }
//         read.close();
//      }else{
//         System.out.println("找不到指定的文件");
//      }
//      String[] strary = arrayList.toArray(new String[0]);




      LSDStringSort lsdStringSort = new LSDStringSort();
      ArrayList<String> ary = new ArrayList<>();
      ary.add("艾珺");
      ary.add("艾爱");
      ary.add("哇");
      ary.add("刘持平");
      ary.add("洪文胜");
      ary.add("阿冰");
      ary.add("阿斌");
      ary.add("阿滨");
      ary.add("阿彬");
      String[] strArr = ary.toArray(new String[0]);
      lsdStringSort.sort(strArr);
      for (String s: strArr){
         System.out.print(s+" ");
      }
//      lsdStringSort.sort(strary);
//
//      PinyinHelper pinyinHelper = new PinyinHelper();
//      pinyinHelper.postProcess(strary);
   }
}
