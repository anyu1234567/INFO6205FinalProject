package sort.linearithmic;

import junit.framework.TestCase;

import sort.GetUnsortedArray;
import sort.PinyinHelper;
import util.OutputIntoFile;

import java.io.IOException;

public class TimSortTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testSort() throws IOException {
        TimSort timSort = new TimSort();
        String[] sorted = (String[]) timSort.sort(GetUnsortedArray.get());
        assertTrue(PinyinHelper.isSorted(sorted));
        OutputIntoFile.writeIntoFile(sorted,"TimTestOutput");
    }
}