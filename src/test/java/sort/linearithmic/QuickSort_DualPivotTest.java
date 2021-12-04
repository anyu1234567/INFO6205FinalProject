package sort.linearithmic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sort.GetUnsortedArray;
import sort.PinyinHelper;

import static org.junit.Assert.*;

public class QuickSort_DualPivotTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sort() {
        QuickSort_DualPivot quickSort_dualPivot = new QuickSort_DualPivot(new PinyinHelper());
        String[] sorted = (String[]) quickSort_dualPivot.sort(GetUnsortedArray.get());
        assertTrue(PinyinHelper.isSorted(sorted));
    }
}