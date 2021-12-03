package sort.counting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sort.linearithmic.TimSortBenchmark;
import util.Config;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LSDStringSortBenchmarkTest {
    LSDStringSortBenchmark benchmark;
    final static String[] args = new String[]{"999999"};
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
}