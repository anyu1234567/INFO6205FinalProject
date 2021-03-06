package sort.huskySort.sort.simple;

import java.util.List;

/**
 * Interface defining partitioning operations.
 *
 * @param <X> the underlying element type.
 */
public interface Partitioner<X extends Comparable<X>> {

    /**
     * Method to partition the given partition into smaller partitions.
     *
     * @param partition the partition to divide up.
     * @return an array of partitions, whose length depends on the sorting method being used.
     */
    List<Partition<X>> partition(Partition<X> partition);
}
