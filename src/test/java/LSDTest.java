
import org.junit.Test;
import sort.counting.LSDStringSort;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class LSDTest {
   @Test
   public void Test1(){
      LSDStringSort lsdStringSort = new LSDStringSort();
      ArrayList<String> ary = new ArrayList<>();
      ary.add("播把");
      ary.add("播啊");
      ary.add("从角度看萨拉");
      ary.add("啊");

      String[] strArr = ary.toArray(new String[0]);
      lsdStringSort.sort(strArr);
      for (String s: strArr){
         System.out.print(s+" ");
      }
   }
}
