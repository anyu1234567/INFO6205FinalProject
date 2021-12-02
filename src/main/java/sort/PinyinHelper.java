package sort;

import util.Config;

import java.nio.ByteBuffer;
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
        for (int i=1;i<xs.length;i++){
            if (collator.compare(xs[i],xs[i-1])<0){
                new HelperException("Array is not sorted");
            }
        }
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

    public static class HelperException extends RuntimeException {

        public HelperException(String message) {
            super(message);
        }

        public HelperException(String message, Throwable cause) {
            super(message, cause);
        }

        public HelperException(Throwable cause) {
            super(cause);
        }

        public HelperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

    public static void main(String[] args) {
        Collator collator = Collator.getInstance(Locale.CHINA);
        CollationKey key = collator.getCollationKey("啊");

        for (byte b : key.toByteArray()) {
            System.out.print(b);
        }
        ByteBuffer buffer = ByteBuffer.wrap(key.toByteArray());
        System.out.println();
        System.out.println(buffer.getLong());
        CollationKey key2 = collator.getCollationKey("从角度看萨拉");
        for (byte b : key2.toByteArray()) {
            System.out.print(b);
        }
        ByteBuffer buffer2 = ByteBuffer.wrap(key2.toByteArray());
        System.out.println();
        long buffer2Long = buffer2.getLong();
        int length = String.valueOf(buffer2Long).length();
        System.out.println(buffer2Long);
        System.out.println(length);
    }
    public Collator collator = Collator.getInstance(Locale.CHINA);
}
