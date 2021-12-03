package sort.counting;

import net.sourceforge.pinyin4j.PinyinHelper;


import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.text.CollationKey;
import java.text.Collator;
import java.util.Locale;

public class LSDStringSort {

    private final int ASCII_RANGE = 256;
    private Collator collator = Collator.getInstance(Locale.CHINA);

    /**
     * findMaxLength method returns maximum length of all available strings in an array
     *
     * @param strArr It contains an array of String from which maximum length needs to be found
     * @return int Returns maximum length value
     */
    private int findMaxLength(String[] strArr) {
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
            return 0;
        }
        String[] pinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(str.charAt(charPosition));
        if (charPinyinPosition>=pinyinStringArray[0].length()){
            return 0;
        }

        return pinyinStringArray[0].charAt(charPosition);
    }

    /**
     * charSort method is implementation of LSD sort algorithm at particular character.
     *
     * @param strArr       It contains an array of String on which LSD char sort needs to be performed
     * @param charPosition This is the character position on which sort would be performed
     * @param from         This is the starting index from which sorting operation will begin
     * @param to           This is the ending index up until which sorting operation will be continued
     */
    private void charSort(String[] strArr, int charPosition, int from, int to,int charPinyinPosition) {
        int[] count = new int[ASCII_RANGE + 2];
        String[] result = new String[strArr.length];

        for (int i = from; i <= to; i++) {
            int c = charAsciiVal(strArr[i], charPosition,charPinyinPosition);
            count[c + 2]++;
        }

        // transform counts to indices
        for (int r = 1; r < ASCII_RANGE + 2; r++)
            count[r] += count[r - 1];

        // distribute
        for (int i = from; i <= to; i++) {
            int c = charAsciiVal(strArr[i], charPosition,charPinyinPosition);
            result[count[c + 1]++] = strArr[i];
        }

        // copy back
        if (to + 1 - from >= 0) System.arraycopy(result, 0, strArr, from, to + 1 - from);
    }

    /**
     * sort method is implementation of LSD String sort algorithm.
     *
     * @param strArr It contains an array of String on which LSD sort needs to be performed
     * @param from   This is the starting index from which sorting operation will begin
     * @param to     This is the ending index up until which sorting operation will be continued
     */
    public void sort(String[] strArr, int from, int to) {
        int maxLength = findMaxLength(strArr);
        for (int i = maxLength - 1; i >= 0; i--){
            int maxPinyinLength = findMaxPinyinLength(strArr,i);
            for (int j = maxPinyinLength-1;j>=0;j--)
                charSort(strArr, i, from, to,j);
        }

    }

    private int findMaxPinyinLength(String[] strArr, int charPosition) {
        int maxPinyinLength = Integer.MIN_VALUE;
        for (String str : strArr){
            if(charPosition>=str.length()){
                continue;
            }
            String[] pinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(str.charAt(charPosition));
            maxPinyinLength= Math.max(pinyinStringArray[0].length(),maxPinyinLength);
        }
        return maxPinyinLength;
    }

    /**
     * sort method is implementation of LSD String sort algorithm.
     *
     * @param strArr It contains an array of String on which LSD sort needs to be performed
     */
    public void sort(String[] strArr) {
        sort(strArr, 0, strArr.length - 1);
    }
}
