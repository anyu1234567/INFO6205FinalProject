import org.junit.Test;
import sort.counting.MSDStringSort;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

public class MSDTest {
 @Test
 public void Test1(){
     ArrayList<String> ary = new ArrayList<>();
     ary.add("播把");
     ary.add("播啊");
     ary.add("从角度看萨拉");
     ary.add("啊");
     ary.add("艾珺");
     ary.add("艾爱");
     ary.add("哇");
     ary.add("刘持平");
     ary.add("洪文胜");
     ary.add("阿冰");
     ary.add("阿斌");
     ary.add("阿滨");
     ary.add("阿彬");
     ary.add("刘持平");
     ary.add("洪文胜");
     ary.add("樊辉辉");
     ary.add("苏会敏");
     ary.add("高民政");
     ary.add("曹玉德");
     ary.add("袁继鹏");
     ary.add("舒冬梅");

     String[] strArr = ary.toArray(new String[0]);
//     Collator collator = Collator.getInstance(Locale.CHINA);
//     int i = collator.compare("播的", "播吧");
//     System.out.println("result:"+i);
     MSDStringSort.sort(strArr);
     for (String s: strArr){
         System.out.print(s+" ");
     }
 }
}
