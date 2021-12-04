package sort.linearithmic;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.Config;


import java.util.Arrays;

public class QuickSort_DualPivotBenchmarkTest extends TestCase {
    QuickSort_DualPivotBenchmark benchmark;
    final static String[] args = new String[]{"999999","23251","46502","95237","190475","380951"};
    @Before
    public void setUp() throws Exception {
        final Config config = Config.load(QuickSort_DualPivotBenchmark.class);
        benchmark = new QuickSort_DualPivotBenchmark();
    }
    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testSortStrings() {

        benchmark.sortStrings(Arrays.stream(args).map(Integer::parseInt), 60000000);
    }
}