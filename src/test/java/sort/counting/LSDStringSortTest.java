package sort.counting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sort.GetUnsortedArray;
import sort.PinyinHelper;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class LSDStringSortTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sort() throws IOException {
        String[] strary = GetUnsortedArray.get();
        LSDStringSort lsdStringSort = new LSDStringSort();
        lsdStringSort.sort(strary);
        assertTrue(PinyinHelper.isSorted(strary,"LSD"));

    }



}