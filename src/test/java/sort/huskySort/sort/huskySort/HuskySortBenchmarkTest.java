package sort.huskySort.sort.huskySort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sort.huskySort.util.Config;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class HuskySortBenchmarkTest {
    HuskySortBenchmark benchmark;
    final static String[] args = new String[]{"1000"};

    @Before
    public void setUp() throws Exception {

        final Config config = Config.load(HuskySortBenchmark.class);
        benchmark = new HuskySortBenchmark(config);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void runBenchmarks() {
    }

    @Test
    public void sortStrings() throws IOException {
        benchmark.sortStrings(Arrays.stream(args).map(Integer::parseInt), 10000);
    }

    @Test
    public void benchmarkStringSorters() {
    }
}