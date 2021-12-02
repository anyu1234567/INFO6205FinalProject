package sort.linearithmic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.Config;

import java.util.Arrays;


public class TimSortBenchmarkTest {
    TimSortBenchmark benchmark;
    final static String[] args = new String[]{"999999"};

    @Before
    public void setUp() throws Exception {
        final Config config = Config.load(TimSortBenchmark.class);
        benchmark = new TimSortBenchmark();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sortStrings() {
        benchmark.sortStrings(Arrays.stream(args).map(Integer::parseInt), 60000000);
    }
}