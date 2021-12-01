package sort.huskySort.sort.simple;

import sort.huskySort.sort.Helper;
import sort.huskySort.sort.SortWithHelper;
import sort.huskySort.util.Config;

import java.util.Arrays;

/**
 * Class to implement Merge Sort.
 * NOTE: this implementation does NOT use the insertion swap mechanism,
 *
 * @param <X> the underlying type to be sorted.
 */
public class MergeSortBasic<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Method to prepare for sorting.
     * The default method invokes init with the length of the array xs then makes a copy of the array if appropriate.
     *
     * @param xs       the original array to be sorted.
     * @param makeCopy true if we need to work on a copy of the array.
     * @return either the original or a copy of the array.
     */
    @Override
    public X[] preSort(X[] xs, boolean makeCopy) {
        // CONSIDER don't copy but just allocate according to the xs/aux interchange optimization
        aux = Arrays.copyOf(xs, xs.length);
        return super.preSort(xs, makeCopy);
    }

    /**
     * Method to sort a sub-array.
     *
     * @param xs   the array to be sorted.
     * @param from the index of the first element of the sub-array.
     * @param to   the index of the first element of the sub-array NOT to sort.
     */
    @Override
    public void sort(X[] xs, int from, int to) {
        @SuppressWarnings("UnnecessaryLocalVariable") int lo = from;
        if (to <= lo + getHelper().cutoff()) {
            insertionSort.sort(xs, from, to);
            return;
        }
        int mid = from + (to - from) / 2;
        sort(xs, lo, mid);
        sort(xs, mid, to);
        System.arraycopy(xs, from, aux, from, to - from);
        getHelper().incrementCopies(to - from);
        merge(aux, xs, lo, mid, to);
    }

    public static final String DESCRIPTION = "MergeSort";

    /**
     * Constructor for MergeSort
     * <p>
     * NOTE this is used only by unit tests, using its own instrumented helper.
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public MergeSortBasic(Helper<X> helper) {
        super(helper);
        insertionSort = new InsertionSort<>(helper);
    }

    /**
     * Constructor for MergeSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public MergeSortBasic(int N, Config config) {
        super(DESCRIPTION, N, config);
        insertionSort = new InsertionSort<>(getHelper());
    }

    private void merge(X[] aux, X[] a, int lo, int mid, int hi) {
        final Helper<X> helper = getHelper();
        int i = lo;
        int j = mid;
        int k = lo;
        for (; k < hi; k++)
            if (i >= mid) helper.copy(aux, j++, a, k);
            else if (j >= hi) helper.copy(aux, i++, a, k);
            else if (helper.less(aux[j], aux[i])) {
                helper.incrementFixes(mid - i);
                helper.copy(aux, j++, a, k);
            } else helper.copy(aux, i++, a, k);
    }

    private X[] aux = null;
    private final InsertionSort<X> insertionSort;
}

