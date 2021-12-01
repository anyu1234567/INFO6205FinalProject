/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package sort.huskySort.sort.simple;

import sort.huskySort.sort.BaseHelper;
import sort.huskySort.sort.Helper;
import sort.huskySort.sort.SortWithHelper;
import sort.huskySort.util.Config;

/**
 * Class to implement Selection Sort.
 * NOTE: this implementation does NOT use the insertion swap mechanism,
 *
 * @param <X> the underlying type to be sorted.
 */
public class SelectionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    public SelectionSort(Helper<X> helper) {
        super(helper);
    }

    /**
     * Constructor for SelectionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public SelectionSort(int N, Config config) {
        super(DESCRIPTION, N, config);
    }

    public SelectionSort() {
        this(new BaseHelper<>(DESCRIPTION));
    }

    /**
     * Constructor for SelectionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public SelectionSort(BaseHelper<X> helper) {
        super(helper);
    }

    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        for (int i = from; i < to; i++) {
            int min = i;
            for (int j = i + 1; j < to; j++)
                if (helper.less(xs[j], xs[min]))
                    min = j;
            helper.swap(xs, i, min);
        }
    }

    public static final String DESCRIPTION = "Selection sort";

}
