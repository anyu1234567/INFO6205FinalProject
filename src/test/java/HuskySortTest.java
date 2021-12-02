import org.junit.BeforeClass;
import org.junit.Test;
import sort.huskySort.sort.huskySort.HuskySortBenchmark;
import sort.huskySort.sort.huskySort.PureHuskySort;
import sort.huskySort.sort.huskySortUtils.HuskyCoderFactory;
import sort.huskySort.sort.huskySortUtils.HuskySequenceCoder;
import sort.huskySort.util.Config;


import java.io.IOException;
import java.util.ArrayList;

public class HuskySortTest {
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
        String[] strArr = ary.toArray(new String[0]);
        HuskySequenceCoder<String> coder = HuskyCoderFactory.utf8ChineseCoder;
        final PureHuskySort<String> pureHuskySort = new PureHuskySort<String>(coder, false, false);
        pureHuskySort.sort(strArr);
        for (String s: strArr){
            System.out.print(s+" ");
        }
    }
    @BeforeClass
    public static void beforeClass() throws IOException {
        config = Config.load();
    }

    private static Config config;
}
