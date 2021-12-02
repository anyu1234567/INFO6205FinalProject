package sort.linearithmic;

import sort.Helper;
import sort.PinyinHelper;
import sort.huskySort.sort.huskySort.QuickHuskySort;
import sort.huskySort.util.Benchmark;
import sort.huskySort.util.LazyLogger;
import sort.huskySort.util.TimeLogger;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sort.huskySort.util.Utilities.*;

public class TimSortBenchmark {
    final static LazyLogger logger = new LazyLogger(TimSortBenchmark.class);
    static final String COMMON_CHINESE_WORDS_CORPUS = "shuffledChinese.txt";
    private static final double LgE = lg(Math.E);

    void sortStrings(final Stream<Integer> wordCounts, final int totalOps) {
        logger.info("sortStrings: beginning String sorts");
        wordCounts.forEach(x -> doSortStrings(x, round(totalOps / minComparisons(x))));
    }
    private void doSortStrings(final int n, final int m) {
        benchmarkStringSorters(COMMON_CHINESE_WORDS_CORPUS, TimSortBenchmark.getWords(COMMON_CHINESE_WORDS_CORPUS, QuickSort_DualPivotBenchmark::lineAsList), n, m,new PinyinHelper());
    }
    /**
     * Method to run pure (non-instrumented) string sorter benchmarks.
     * <p>
     * NOTE: this is package-private because it is used by unit tests.
     * <p>
     * CONSIDER merging this with compareSystemAndPureHuskySorts
     *
     * @param corpus     the name of the corpus file to be used as a source of Strings.
     * @param words      the word source.
     * @param nWords     the number of words to be sorted.
     * @param nRuns      the number of runs.
     * @param helper the Husky coder to use in the test of PureHuskySort.
     */
    void benchmarkStringSorters(final String corpus, final String[] words, final int nWords, final int nRuns, Helper helper) {
        logger.info("benchmarkStringSorters: testing pure sorts with " + formatWhole(nRuns) + " runs of sorting " + formatWhole(nWords) + " words using coder: " + helper.getClass().getSimpleName());
        final Random random = new Random();
        //final boolean preSorted = isConfigBenchmarkStringSorter("presorted");
        final boolean preSorted = false;
        final String s2 = ") words from " + corpus;


        //final boolean purehuskysortwithinsertionsort = isConfigBenchmarkStringSorter("purehuskysortwithinsertionsort");
        final boolean purTimsort = false;
        final TimSort timSort = new TimSort(helper);
        final String s1 = "TimSort" + (purTimsort ? " with insertion sort" : "");
        final Benchmark<String[]> benchmark = new Benchmark<>(getDescription(nWords, s1, s2), null, timSort::sort, null);
        doPureBenchmark(words, nWords, nRuns, random, benchmark, preSorted);


    }
    private static void doPureBenchmark(final String[] words, final int nWords, final int nRuns, final Random random, final Benchmark<String[]> benchmark, final boolean preSorted) {
        final double time = benchmark.run(getWordSupplier(words, nWords, random, preSorted), nRuns);
        logger.info("CSV, " + benchmark + ", " + nWords + ", " + time);
        for (final TimeLogger timeLogger : timeLoggersLinearithmic) timeLogger.log(time, nWords);
    }

    private static Supplier<String[]> getWordSupplier(final String[] words, final int nWords, final Random random, final boolean preSorted) {
        // NOTE that the preSorted branch does not seem to work correctly with Chinese text.
        if (preSorted) {
            final String[] strings = Arrays.copyOf(words, Math.min(nWords, words.length));
            return () -> strings;
        } else return () -> fillRandomArray(String.class, random, nWords, r -> words[r.nextInt(words.length)]);
    }
    /**
     * For mergesort, the number of array accesses is actually 6 times the number of comparisons.
     * That's because, in addition to each comparison, there will be approximately two copy operations.
     * Thus, in the case where comparisons are based on primitives,
     * the normalized time per run should approximate the time for one array access.
     */
    public final static TimeLogger[] timeLoggersLinearithmic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n log n): ", (time, n) -> time / minComparisons(n) / 6 * 1e6)
    };
    private static String getDescription(final int nWords, final String s1, final String s2) {
        return s1 + " (" + nWords + s2;
    }

    /**
     * This is based on log2(n!)
     *
     * @param n the number of elements.
     * @return the minimum number of comparisons possible to sort n randomly ordered elements.
     */
    static double minComparisons(final int n) {
        final double lgN = lg(n);
        return n * (lgN - LgE) + lgN / 2 + 1.33;
    }
    static List<String> lineAsList(final String line) {
        final List<String> words = new ArrayList<>();
        words.add(line);
        return words;
    }
    static String[] getWords(final String resource, final Function<String, List<String>> stringListFunction) {
        try {
            final File file = new File(getPathname(resource, QuickHuskySort.class));
            final String[] result = getWordArray(file, stringListFunction, 2);
            logger.info("getWords: testing with " + formatWhole(result.length) + " unique words: from " + file);
            return result;
        } catch (final FileNotFoundException e) {
            logger.warn("Cannot find resource: " + resource, e);
            return new String[0];
        }
    }

    private static String getPathname(final String resource, @SuppressWarnings("SameParameterValue") final Class<?> clazz) throws FileNotFoundException {
        final URL url = clazz.getClassLoader().getResource(resource);
        if (url != null) return url.getPath();
        throw new FileNotFoundException(resource + " in " + clazz);
    }


    /**
     * Method to read given file and return a String[] of its content.
     *
     * @param file               the file to read.
     * @param stringListFunction a function which takes a String and splits into a List of Strings.
     * @param minLength          the minimum acceptable length for a word.
     * @return an array of Strings.
     */
    static String[] getWordArray(final File file, final Function<String, List<String>> stringListFunction, final int minLength) {
        try (final FileReader fr = new FileReader(file)) {
            return getWordList(fr, stringListFunction, minLength).toArray(new String[0]);
        } catch (final IOException e) {
            logger.warn("Cannot open file: " + file, e);
            return new String[0];
        }
    }
    private static List<String> getWordList(final FileReader fr, final Function<String, List<String>> stringListFunction, final int minLength) {
        final List<String> words = new ArrayList<>();
        for (final Object line : new BufferedReader(fr).lines().toArray())
            words.addAll(stringListFunction.apply((String) line));
        return words.stream().distinct().filter(s -> s.length() >= minLength).collect(Collectors.toList());
    }
}
