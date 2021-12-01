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

     String[] strArr = ary.toArray(new String[0]);
     Collator collator = Collator.getInstance(Locale.CHINA);
     int i = collator.compare("播的", "播吧");
     System.out.println("result:"+i);
     MSDStringSort.sort(strArr);
     for (String s: strArr){
         System.out.print(s+" ");
     }
 }
}
