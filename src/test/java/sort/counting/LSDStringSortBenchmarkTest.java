package sort.counting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sort.GetUnsortedArray;
import sort.huskySort.util.LazyLogger;
import sort.linearithmic.TimSortBenchmark;
import util.Config;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LSDStringSortBenchmarkTest {
    LSDStringSortBenchmark benchmark;
    // set different size include  250k,500k,1M,2M,4M,10M
    final static String[] args = new String[]{"23251","46502","95237","190475","380951","999999"};
    @Before
    public void setUp() throws Exception {
        final Config config = Config.load(LSDStringSortBenchmark.class);
        benchmark = new LSDStringSortBenchmark();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sortStrings() {
        benchmark.sortStrings(Arrays.stream(args).map(Integer::parseInt), 60000000);
    }

    public static LazyLogger logger = new LazyLogger(GetUnsortedArray.class);
}