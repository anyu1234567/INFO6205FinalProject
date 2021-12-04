package sort.counting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sort.GetUnsortedArray;
import sort.PinyinHelper;
import sort.huskySort.util.LazyLogger;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static sort.huskySort.util.Utilities.isSorted;

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

    }



}