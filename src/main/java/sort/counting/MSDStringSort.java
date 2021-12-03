package sort.counting;

import net.sourceforge.pinyin4j.PinyinHelper;
import sort.elementary.InsertionSortMSD;

/**
 * Class to implement Most significant digit string sort (a radix sort).
 */
public class MSDStringSort {

    /**
     * Sort an array of Strings using MSDStringSort.
     *
     * @param a the array to be sorted.
     */
    public static void sort(String[] a) {
        int n = a.length;
        aux = new String[n];

        sort(a, 0, n, 0,0);


    }
    /**
     * findMaxLength method returns maximum length of all available strings in an array
     *
     * @param strArr It contains an array of String from which maximum length needs to be found
     * @return int Returns maximum length value
     */
    public static int findMaxLength(String[] strArr) {
        int maxLength = strArr[0].length();
        for (String str : strArr)
            maxLength = Math.max(maxLength, str.length());
        return maxLength;
    }
    /**
     * charAsciiVal method returns ASCII value of particular character in a String.
     *
     * @param str          String input for which ASCII Value need to be found
     * @param charPosition Character position of which ASCII value needs to be found. If character
     *                     doesn't exist then ASCII value of null i.e. 0 is returned
     * @return int Returns ASCII value
     */
    private int charAsciiVal(String str, int charPosition,int charPinyinPosition) {
        if (charPosition >= str.length()) {
            return -1;
        }
        String[] pinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(str.charAt(charPosition));
        if (charPinyinPosition>=pinyinStringArray[0].length()){
            return -1;
        }

        return pinyinStringArray[0].charAt(charPosition);
    }

    /**
     * Sort from a[lo] to a[hi] (exclusive), ignoring the first d characters of each String.
     * This method is recursive.
     *
     * @param a the array to be sorted.
     * @param lo the low index.
     * @param hi the high index (one above the highest actually processed).
     * @param d the number of characters in each String to be skipped.
     */
    private static void sort(String[] a, int lo, int hi, int d,int charPinyinPosition) {
        if (hi < lo + cutoff) InsertionSortMSD.sort(a, lo, hi, d);
        else {
            int[] count = new int[radix + 2];        // Compute frequency counts.
            for (int i = lo; i < hi; i++)
                count[charAt(a[i], d,charPinyinPosition) + 2]++;
            for (int r = 0; r < radix + 1; r++)      // Transform counts to indices.
                count[r + 1] += count[r];
            for (int i = lo; i < hi; i++)     // Distribute.
                aux[count[charAt(a[i], d,charPinyinPosition) + 1]++] = a[i];
            // Copy back.
            if (hi - lo >= 0) System.arraycopy(aux, 0, a, lo, hi - lo);
            for (String s:a){
                System.out.print(s+" ");
            }
            System.out.println();
            System.out.println();
            // Recursively sort for each character value.
            // TO BE IMPLEMENTED
            for(int r =0;r<radix;r++){
                if(count[r]==0){
                    continue;
                }
                while(charPinyinPosition<=6){
                    sort(a,lo+count[r],lo+count[r+1]-1,d,++charPinyinPosition);
                }
                charPinyinPosition=0;
                sort(a,lo+count[r],lo+count[r+1]-1,d+1,charPinyinPosition);

            }
            //Collator c = Collator.getInstance(Locale.CHINA);
        }
    }

    private static int charAt(String s, int charPosition,int charPinyinPosition) {
        if (charPosition < s.length()){
            String[] pinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(s.charAt(charPosition));
            if(charPinyinPosition>=pinyinStringArray[0].length()){
                return -1;
            }
            System.out.println(s.charAt(charPosition)+":"+pinyinStringArray[0].charAt(charPinyinPosition));
            return pinyinStringArray[0].charAt(charPinyinPosition);
        }else return -1;
    }

    private static final int radix = 256;
    private static final int cutoff = 0;
    private static String[] aux;       // auxiliary array for distribution
}