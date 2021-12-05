package sort.counting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sort.GetUnsortedArray;
import sort.PinyinHelper;
import util.OutputIntoFile;

import static org.junit.Assert.*;

public class MSDStringSortTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sort() {
        String[] strary = GetUnsortedArray.get();
        MSDStringSort.sort(strary);
        assertTrue(PinyinHelper.isSorted(strary,"MSD"));
        OutputIntoFile.writeIntoFile(strary,"MSDTestOutput");
    }
}