package sort;

import util.Config;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Locale;
import java.util.function.Function;

public class PinyinHelper<X extends  Comparable<X>> implements Helper {

    @Override
    public boolean instrumented() {
        return true;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Config getConfig() {
        return null;
    }

    @Override
    public int getN() {
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public Object[] random(Class clazz, Function f) {
        return new Object[0];
    }

    @Override
    public int compare(Comparable[] xs, int i, int j) {
        return collator.compare(xs[i],xs[j]);
    }

    @Override
    public boolean less(Comparable v, Comparable w) {
        return collator.compare(v,w)<0;
    }

    @Override
    public int compare(Comparable v, Comparable w) {
        return 0;
    }

    @Override
    public void swapInto(Comparable[] xs, int i, int j) {

    }

    @Override
    public boolean sorted(Comparable[] xs) {
        return false;
    }

    @Override
    public int inversions(Comparable[] xs) {
        return 0;
    }

    @Override
    public void postProcess(Comparable[] xs) {

    }

    @Override
    public void init(int n) {

    }

    @Override
    public boolean swapConditional(Comparable[] xs, int i, int j) {
        Comparable x = xs[i];
        Comparable y = xs[j];
        boolean result = collator.compare(xs[i], xs[j]) >0;
        if(result){
            xs[i] =y;
            xs[j] =x;
        }
        return result;
    }

    @Override
    public boolean swapStableConditional(Comparable[] xs, int i) {
        Comparable x = xs[i];
        Comparable y = xs[i-1];
        boolean result = collator.compare(x,y)< 0;
        if (result) {
            xs[i] = y;
            xs[i - 1] = x;
        }
        return result;
    }


    public static void main(String[] args) {
        Collator collator = Collator.getInstance(Locale.CHINA);
        CollationKey key = collator.getCollationKey("啊啊");

        for (byte b : key.toByteArray()) {
            System.out.print(b);
        }
        System.out.println();
        CollationKey key2 = collator.getCollationKey("啊不");
        for (byte b : key2.toByteArray()) {
            System.out.print(b);
        }

    }
    public Collator collator = Collator.getInstance(Locale.CHINA);
}
