package sort.huskySort.sort.huskySort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sort.GetUnsortedArray;
import sort.PinyinHelper;
import sort.huskySort.sort.huskySortUtils.HuskyCoderFactory;
import sort.huskySort.sort.huskySortUtils.HuskySequenceCoder;
import util.OutputIntoFile;

import static org.junit.Assert.*;

public class PureHuskySortTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sort() {
        HuskySequenceCoder<String> coder = HuskyCoderFactory.utf8ChineseCoder;
        PureHuskySort pureHuskySort = new PureHuskySort(coder,false,false);
        String[]xs = GetUnsortedArray.get();
        pureHuskySort.sort(xs);
        assertTrue(PinyinHelper.isSorted(xs));
        OutputIntoFile.writeIntoFile(xs,"HuskyTestOutput");
    }
}