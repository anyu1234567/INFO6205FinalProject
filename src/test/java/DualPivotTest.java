import org.junit.BeforeClass;
import org.junit.Test;
import sort.GenericSort;
import sort.PinyinHelper;
import sort.SortWithHelper;
import sort.counting.LSDStringSort;
import sort.linearithmic.QuickSort_DualPivot;
import util.Config;
import util.LazyLogger;

import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

public class DualPivotTest {
    @Test
    public void Test1(){

        ArrayList<String> ary = new ArrayList<>();
        ary.add("播把");
        ary.add("播啊");
        ary.add("从角度看萨拉");
        ary.add("啊");
        ary.add("播把");
        ary.add("播啊");
        ary.add("从角度看萨拉");
        ary.add("啊");
        ary.add("播把");
        ary.add("播啊");
        ary.add("从角度看萨拉");
        ary.add("啊");
        Collator collator = Collator.getInstance(Locale.CHINA);
        int compare = collator.compare("从角度看萨拉", "播啊");
        System.out.println("result: "+compare);
        String[] strArr = ary.toArray(new String[0]);
        SortWithHelper<String> sorter =new QuickSort_DualPivot(new PinyinHelper());
        String[] sorted = sorter.sort(strArr);
        for (String s: sorted){
            System.out.print(s+" ");
        }
    }

}
